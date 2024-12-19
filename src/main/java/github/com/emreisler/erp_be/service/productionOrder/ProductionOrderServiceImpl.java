package github.com.emreisler.erp_be.service.productionOrder;

import github.com.emreisler.erp_be.converters.ProductionOrderConverter;
import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.entity.Operation;
import github.com.emreisler.erp_be.entity.Part;
import github.com.emreisler.erp_be.entity.ProductionOrder;
import github.com.emreisler.erp_be.enums.ProductionOrderStatus;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.PartRepository;
import github.com.emreisler.erp_be.repository.ProductionOrderRepository;
import github.com.emreisler.erp_be.validators.Validator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductionOrderServiceImpl implements ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;
    private final PartRepository partRepository;
    private final Validator<CreateProductionOrderRequest> validator;


    public ProductionOrderServiceImpl(ProductionOrderRepository productionOrderRepository, PartRepository partRepository, Validator<CreateProductionOrderRequest> validator) {
        this.productionOrderRepository = productionOrderRepository;
        this.partRepository = partRepository;
        this.validator = validator;
    }

    public List<ProductionOrderDto> getAll() {
        List<ProductionOrder> productionOrders = productionOrderRepository.findAll();
        return productionOrders.stream()
                .map(ProductionOrderConverter::toDto)
                .toList();
    }

    public List<ProductionOrderDto> getByPartNo(String partNumber) {
        List<ProductionOrder> productionOrders = productionOrderRepository.findByPartNumber(partNumber);
        return productionOrders.stream()
                .map(ProductionOrderConverter::toDto)
                .toList();
    }

    @Override
    public ProductionOrderDto getByCode(String code) throws ErpRuntimeException {
        var po = productionOrderRepository.findByCode(code).orElseThrow(() -> new NotFoundException("ProductionOrder not found for code: " + code));
        return ProductionOrderConverter.toDto(po);
    }

    @Override
    public ProductionOrderDto update(ProductionOrderDto productionOrderDto) throws ErpRuntimeException {
        var updatedPo = productionOrderRepository.findByCode(productionOrderDto.getCode()).map(po -> {
            po.setQuantity(productionOrderDto.getQuantity());
            po.setTotalSteps(productionOrderDto.getTotalSteps());
            po.setEndDate(productionOrderDto.getEndDate());
            po.setPartNumber(productionOrderDto.getPartNumber());
            po.setStatus(productionOrderDto.getStatus());
            po.setCurrentStep(productionOrderDto.getCurrentStep());
            po.setCurrentTaskCenter(productionOrderDto.getCurrentTaskCenter());
            return productionOrderRepository.save(po);
        }).orElseThrow(() -> new NotFoundException("ProductionOrder not found for code: " + productionOrderDto.getCode()));
        return ProductionOrderConverter.toDto(updatedPo);
    }

    public List<ProductionOrderDto> getByCurrentTaskCenterNo(int currentTaskCenter) {
        List<ProductionOrder> productionOrders = productionOrderRepository.findByCurrentTaskCenter(currentTaskCenter);
        return productionOrders.stream()
                .map(ProductionOrderConverter::toDto)
                .toList();
    }

    @Transactional
    public ProductionOrderDto create(CreateProductionOrderRequest request) {

        validator.validate(request);

        // Generate UUID and Code
        UUID orderId = UUID.randomUUID();
        String code = "PO-" + orderId.toString().substring(0, 8).toUpperCase();

        // Fetch part details and determine the current step
        Part part = partRepository.findByNumber(request.getPartNo())
                .orElseThrow(() -> new NotFoundException("Part not found for partNo: " + request.getPartNo()));

        //if part has no operations production order can not be created
        if (part.getOperationList().isEmpty()) {
            throw new NotFoundException("No operations found for partNo: " + request.getPartNo());
        }

        var sortedOperations = part.getOperationList().stream().sorted(Comparator.comparingInt(Operation::getSepNumber)).toList();
        var firstOperation = sortedOperations.get(0);

        int currentStep = firstOperation.getSepNumber();
        int currentTaskCenter = firstOperation.getTaskCenterNo();

        // Create and populate ProductionOrder entity
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderId(orderId);
        productionOrder.setCode(code);
        productionOrder.setPartNumber(request.getPartNo());
        productionOrder.setQuantity(request.getQuantity());
        productionOrder.setStatus(ProductionOrderStatus.CREATED);
        productionOrder.setCurrentStep(currentStep);
        productionOrder.setTotalSteps(part.getOperationList().size());
        productionOrder.setCurrentTaskCenter(currentTaskCenter);
        productionOrder.setEndDate(request.getEndDate());

        try {
            ProductionOrder savedOrder = productionOrderRepository.save(productionOrder);
            return ProductionOrderConverter.toDto(savedOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving production order");
        }
    }
}
