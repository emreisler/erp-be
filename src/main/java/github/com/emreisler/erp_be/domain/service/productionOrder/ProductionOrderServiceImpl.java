package github.com.emreisler.erp_be.domain.service.productionOrder;

import github.com.emreisler.erp_be.application.dto.*;
import github.com.emreisler.erp_be.converters.ProductionOrderConverter;
import github.com.emreisler.erp_be.converters.StampConverter;
import github.com.emreisler.erp_be.domain.enums.ProductionOrderStatus;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import github.com.emreisler.erp_be.domain.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.domain.exception.NotFoundException;
import github.com.emreisler.erp_be.domain.service.part.PartService;
import github.com.emreisler.erp_be.domain.service.taskCenter.TaskCenterService;
import github.com.emreisler.erp_be.domain.validators.Validator;
import github.com.emreisler.erp_be.persistence.entity.*;
import github.com.emreisler.erp_be.persistence.repository.AssemblyRepository;
import github.com.emreisler.erp_be.persistence.repository.PartRepository;
import github.com.emreisler.erp_be.persistence.repository.ProductionOrderRepository;
import github.com.emreisler.erp_be.persistence.repository.StampRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductionOrderServiceImpl implements ProductionOrderService {

    private final PartService partService;
    private final TaskCenterService taskCenterService;
    private final ProductionOrderRepository productionOrderRepository;
    private final PartRepository partRepository;
    private final AssemblyRepository assemblyRepository;
    private final StampRepository stampRepository;
    private final Validator<CreatePartProductionOrderRequest> validator;


    public ProductionOrderServiceImpl(PartService partService, TaskCenterService taskCenterService, ProductionOrderRepository productionOrderRepository, PartRepository partRepository, AssemblyRepository assemblyRepository, StampRepository stampRepository, Validator<CreatePartProductionOrderRequest> validator) {
        this.partService = partService;
        this.taskCenterService = taskCenterService;
        this.productionOrderRepository = productionOrderRepository;
        this.partRepository = partRepository;
        this.assemblyRepository = assemblyRepository;
        this.validator = validator;
        this.stampRepository = stampRepository;
    }

    public List<ProductionOrderDto> getAll() {
        List<ProductionOrder> productionOrders = productionOrderRepository.findAllByOrderByEndDateAsc();
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
    public ProductionOrderDto createPartProductionOrder(CreatePartProductionOrderRequest request) {

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

        var sortedOperations = part.getOperationList().stream().sorted(Comparator.comparingInt(Operation::getStepNumber)).toList();
        var firstOperation = sortedOperations.get(0);

        int currentStep = firstOperation.getStepNumber();
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

    @Override
    @Transactional
    public ProductionOrderDto createAssemblyProductionOrder(CreateAssemblyProductionOrderRequest request) {

        // Generate UUID and Code
        UUID orderId = UUID.randomUUID();
        String code = "PO-" + orderId.toString().substring(0, 8).toUpperCase();

        // Fetch part details and determine the current step
        Assembly assembly = assemblyRepository.findByNumber(request.getAssemblyNo())
                .orElseThrow(() -> new NotFoundException("Assembly not found for partNo: " + request.getAssemblyNo()));

        //if part has no operations production order can not be created
        if (assembly.getOperationList().isEmpty()) {
            throw new NotFoundException("No operations found for assembly: " + request.getAssemblyNo());
        }

        var sortedOperations = assembly.getOperationList().stream().sorted(Comparator.comparingInt(Operation::getStepNumber)).toList();
        var firstOperation = sortedOperations.get(0);

        int currentStep = firstOperation.getStepNumber();
        int currentTaskCenter = firstOperation.getTaskCenterNo();

        // Create and populate ProductionOrder entity
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderId(orderId);
        productionOrder.setCode(code);
        productionOrder.setAssemblyNumber(request.getAssemblyNo());
        productionOrder.setQuantity(request.getQuantity());
        productionOrder.setStatus(ProductionOrderStatus.CREATED);
        productionOrder.setCurrentStep(currentStep);
        productionOrder.setTotalSteps(assembly.getOperationList().size());
        productionOrder.setCurrentTaskCenter(currentTaskCenter);
        productionOrder.setEndDate(request.getEndDate());

        try {
            ProductionOrder savedOrder = productionOrderRepository.save(productionOrder);
            return ProductionOrderConverter.toDto(savedOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving production order");
        }
    }

    @Override
    @Transactional
    public ProductionOrderDto stamp(StampDto stampDto) throws Exception {

//        validator.validate(stampDto);//todo validate stamp

        var prodOrder = productionOrderRepository.findByCode(stampDto.getProductionOrderCode())
                .orElseThrow(() -> new NotFoundException("ProductionOrder not found for code: " + stampDto.getProductionOrderCode()));

        var part = partService.GetByNumber(prodOrder.getPartNumber());

        if (part.getOperationList().isEmpty()) {
            throw new BadRequestException("part has no operation to stamp");
        }

        if (this.isStamped(prodOrder, stampDto.getStepNumber())) {
            throw new BadRequestException("step is already stamped");
        }

        var sortedOperations = part.getOperationList()
                .stream()
                .sorted(Comparator.comparingInt(OperationDto::getStepNumber))
                .toList();

        int previousStep = 0;
        var stepExist = false;
        var currentTcNo = 0;
        for (var operation : sortedOperations) {
            if (stepExist) {
                currentTcNo = operation.getTaskCenterNo();
                break;
            }
            if (stampDto.getStepNumber() == operation.getStepNumber()) {
                stepExist = true;
            }
            previousStep = operation.getStepNumber();
        }

        if (!stepExist) {
            throw new BadRequestException("step not exist");
        }

        prodOrder.setCurrentStep(stampDto.getStepNumber());
        prodOrder.setCurrentTaskCenter(currentTcNo);
//
//        if (previousStep != 0 && !this.isStamped(prodOrder, previousStep)) {
//            throw new BadRequestException("previous step is not stamped");
//        }

        if (sortedOperations.get(sortedOperations.size() - 1).getStepNumber() == stampDto.getStepNumber()) {
            prodOrder.setStatus(ProductionOrderStatus.COMPLETED);
        } else {
            prodOrder.setStatus(ProductionOrderStatus.IN_PROGRESS);
        }

        var stamp = StampConverter.toEntity(stampDto);
        stamp.setProductionOrder(prodOrder);


        try {
            var persistedStamp = stampRepository.save(stamp);
            var stamps = prodOrder.getStampList();
            stamps.add(persistedStamp);
            prodOrder.setStampList(stamps);
            var updatedOrder = productionOrderRepository.save(prodOrder);
            return ProductionOrderConverter.toDto(updatedOrder);
        } catch (Exception e) {
            throw new ErpRuntimeException(e.getMessage());
        }
    }

    @Override
    public ProductionOrderDto removeStamp(String poCode, int stepNumber) throws Exception {
        stampRepository.deleteByProductionOrderCodeAndStepNumber(poCode, stepNumber);
        return ProductionOrderConverter.toDto(productionOrderRepository.findByCode(poCode).orElseThrow(() -> new NotFoundException("ProductionOrder not found for code: " + poCode)));
    }

    @Override
    public List<StampDto> getStampsByCode(String code) throws Exception {
        return stampRepository.findByProductionOrderCode(code).stream().map(StampConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskCenterPartCount> getTaskCenterMetrics() throws Exception {
        var prodOrders = productionOrderRepository.findAll();
        var taskCenters = taskCenterService.GetAll();

        if (prodOrders.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, TaskCenterPartCount> taskCenterPartCountMap = new HashMap<>();

        for (var tc : taskCenters) {
            taskCenterPartCountMap.put(tc.getNumber(), new TaskCenterPartCount(tc));
        }

        for (var prodOrder : prodOrders) {
            if (prodOrder.getPartNumber() == null || prodOrder.getPartNumber().isEmpty() || prodOrder.getCurrentTaskCenter() == 0) {
                continue;
            }

            var partDto = partService.GetByNumber(prodOrder.getPartNumber());

            taskCenterPartCountMap.get(prodOrder.getCurrentTaskCenter()).addPart(partDto);
            taskCenterPartCountMap.get(prodOrder.getCurrentTaskCenter()).increaseQuantity(prodOrder.getQuantity());

        }


        return new ArrayList<>(taskCenterPartCountMap.values());
    }

    @Override
    public List<TaskCenterDto> getIdleTaskCenters() throws Exception {
        var taskCenters = taskCenterService.GetAll();
        var prodOrders = productionOrderRepository.findAll();

        Set<Integer> busyTaskCenters = new HashSet<>();

        for (var prodOrder : prodOrders) {
            busyTaskCenters.add(prodOrder.getCurrentTaskCenter());
        }

        List<TaskCenterDto> idleTaskCenters = new ArrayList<>();
        for (var taskCenter : taskCenters) {
            if (!busyTaskCenters.contains(taskCenter.getNumber())) {
                idleTaskCenters.add(taskCenter);
            }
        }

        return idleTaskCenters;

    }

    private boolean isStamped(ProductionOrder productionOrder, int stepNumber) {
        for (Stamp stamp : productionOrder.getStampList()) {
            if (stamp.getStepNumber() == stepNumber) {
                return true;
            }
        }
        return false;
    }

}
