package github.com.emreisler.erp_be.service.productionOrder;

import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;

import java.util.List;

public interface ProductionOrderService {
    List<ProductionOrderDto> getAll();

    List<ProductionOrderDto> getByPartNo(String partNumber);

    ProductionOrderDto getByCode(String code) throws ErpRuntimeException;

    ProductionOrderDto update(ProductionOrderDto productionOrderDto) throws ErpRuntimeException;

    List<ProductionOrderDto> getByCurrentTaskCenterNo(int currentTaskCenter);

    ProductionOrderDto create(CreateProductionOrderRequest request);

    ProductionOrderDto stamp(StampDto stampDto) throws Exception;

    ProductionOrderDto removeStamp(String poCode, int stepNumber) throws Exception;

    List<StampDto> getStampsByCode(String code) throws Exception;
}
