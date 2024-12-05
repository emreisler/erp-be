package github.com.emreisler.erp_be.service.productionOrder;

import github.com.emreisler.erp_be.converters.ProductionOrderConverter;
import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.entity.Part;
import github.com.emreisler.erp_be.entity.ProductionOrder;
import github.com.emreisler.erp_be.enums.ProductionOrderStatus;
import github.com.emreisler.erp_be.repository.PartRepository;
import github.com.emreisler.erp_be.repository.ProductionOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductionOrderServiceImpl implements ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;
    private final PartRepository partRepository;


    public ProductionOrderServiceImpl(ProductionOrderRepository productionOrderRepository, PartRepository partRepository) {
        this.productionOrderRepository = productionOrderRepository;
        this.partRepository = partRepository;
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

    public List<ProductionOrderDto> getByCurrentTaskCenterNo(int currentTaskCenter) {
        List<ProductionOrder> productionOrders = productionOrderRepository.findByCurrentTaskCenter(currentTaskCenter);
        return productionOrders.stream()
                .map(ProductionOrderConverter::toDto)
                .toList();
    }

    @Transactional
    public ProductionOrderDto create(CreateProductionOrderRequest request) {
        // Generate UUID and Code
        UUID orderId = UUID.randomUUID();
        String code = "PO-" + orderId.toString().substring(0, 8).toUpperCase();

        // Fetch part details and determine the current step
        Part part = partRepository.findByNumber(request.getPartNo())
                .orElseThrow(() -> new IllegalArgumentException("Part not found for partNo: " + request.getPartNo()));

        //if part has no operations production order can not be created
        if (part.getOperationList().isEmpty()) {
            throw new IllegalArgumentException("No operations found for partNo: " + request.getPartNo());
        }
        int currentStep = part.getOperationList() != null && !part.getOperationList().isEmpty()
                ? part.getOperationList().get(0).getSepNumber()
                : 0; // Default to 0 if no operations are present
        int currentTaskCenter = part.getOperationList() != null && !part.getOperationList().isEmpty()
                ? part.getOperationList().get(0).getTaskCenterNo()
                : 0;
        // Create and populate ProductionOrder entity
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderId(orderId);
        productionOrder.setCode(code);
        productionOrder.setPartNumber(request.getPartNo());
        productionOrder.setQuantity(request.getQuantity());
        productionOrder.setStatus(ProductionOrderStatus.CREATED);
        productionOrder.setCurrentStep(currentStep);
        productionOrder.setTotalSteps(part.getOperationList().size()); // Total steps from operations list
        productionOrder.setCurrentTaskCenter(currentTaskCenter); // Default task center
        productionOrder.setEndDate(request.getEndDate()); // Set end date from the request

        // Save the entity and convert to DTO
        try {
            ProductionOrder savedOrder = productionOrderRepository.save(productionOrder);
            return ProductionOrderConverter.toDto(savedOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving production order");
        }

    }
}
