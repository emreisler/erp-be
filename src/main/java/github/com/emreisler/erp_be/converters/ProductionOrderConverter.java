package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.entity.ProductionOrder;

public class ProductionOrderConverter {

    public static ProductionOrderDto toDto(ProductionOrder productionOrder) {
        ProductionOrderDto dto = new ProductionOrderDto();
        dto.setOrderId(productionOrder.getOrderId());
        dto.setCode(productionOrder.getCode());
        dto.setPartNumber(productionOrder.getPartNumber());
        dto.setQuantity(productionOrder.getQuantity());
        dto.setStatus(productionOrder.getStatus());
        dto.setCurrentStep(productionOrder.getCurrentStep());
        dto.setTotalSteps(productionOrder.getTotalSteps());
        dto.setCurrentTaskCenter(productionOrder.getCurrentTaskCenter());
        dto.setEndDate(productionOrder.getEndDate());
        return dto;
    }

    public static ProductionOrder toEntity(ProductionOrderDto dto) {
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderId(dto.getOrderId());
        productionOrder.setCode(dto.getCode());
        productionOrder.setPartNumber(dto.getPartNumber());
        productionOrder.setQuantity(dto.getQuantity());
        productionOrder.setStatus(dto.getStatus());
        productionOrder.setCurrentStep(dto.getCurrentStep());
        productionOrder.setTotalSteps(dto.getTotalSteps());
        productionOrder.setCurrentTaskCenter(dto.getCurrentTaskCenter());
        productionOrder.setEndDate(dto.getEndDate());
        return productionOrder;
    }
}
