package github.com.emreisler.erp_be.service.productionOrder;

import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;

import java.util.List;

public interface ProductionOrderService {
    List<ProductionOrderDto> getAll();
    List<ProductionOrderDto> getByPartNo(String partNumber);
    List<ProductionOrderDto> getByCurrentTaskCenterNo(int currentTaskCenter);
    ProductionOrderDto create(CreateProductionOrderRequest request);
}
