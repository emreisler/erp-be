package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.application.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.persistence.entity.ProductionOrder;

import java.util.stream.Collectors;

public class ProductionOrderConverter {

    public static ProductionOrderDto toDto(ProductionOrder productionOrder) {
        ProductionOrderDto dto = new ProductionOrderDto();
        dto.setOrderId(productionOrder.getOrderId());
        dto.setCode(productionOrder.getCode());
        dto.setPartNumber(productionOrder.getPartNumber());
        dto.setAssemblyNumber(productionOrder.getAssemblyNumber());
        dto.setQuantity(productionOrder.getQuantity());
        dto.setStatus(productionOrder.getStatus());
        dto.setCurrentStep(productionOrder.getCurrentStep());
        dto.setTotalSteps(productionOrder.getTotalSteps());
        dto.setCurrentTaskCenter(productionOrder.getCurrentTaskCenter());
        dto.setEndDate(productionOrder.getEndDate());
        if (productionOrder.getStampList() != null && !productionOrder.getStampList().isEmpty()) {
            dto.setStampList(productionOrder.getStampList().stream().map(StampConverter::toDto).collect(Collectors.toList()));
        }

        return dto;
    }

    public static ProductionOrder toEntity(ProductionOrderDto dto) {
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderId(dto.getOrderId());
        productionOrder.setCode(dto.getCode());
        productionOrder.setPartNumber(dto.getPartNumber());
        productionOrder.setAssemblyNumber(dto.getAssemblyNumber());
        productionOrder.setQuantity(dto.getQuantity());
        productionOrder.setStatus(dto.getStatus());
        productionOrder.setCurrentStep(dto.getCurrentStep());
        productionOrder.setTotalSteps(dto.getTotalSteps());
        productionOrder.setCurrentTaskCenter(dto.getCurrentTaskCenter());
        productionOrder.setEndDate(dto.getEndDate());
        productionOrder.setStampList(dto.getStampList().stream().map(StampConverter::toEntity).collect(Collectors.toList()));
        return productionOrder;
    }
}
