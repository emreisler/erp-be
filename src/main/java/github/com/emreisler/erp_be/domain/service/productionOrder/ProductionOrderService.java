package github.com.emreisler.erp_be.domain.service.productionOrder;

import github.com.emreisler.erp_be.application.dto.*;
import github.com.emreisler.erp_be.domain.exception.ErpRuntimeException;

import java.util.List;

public interface ProductionOrderService {
    List<ProductionOrderDto> getAll();

    List<ProductionOrderDto> getByPartNo(String partNumber);

    ProductionOrderDto getByCode(String code) throws ErpRuntimeException;

    ProductionOrderDto update(ProductionOrderDto productionOrderDto) throws ErpRuntimeException;

    List<ProductionOrderDto> getByCurrentTaskCenterNo(int currentTaskCenter);

    ProductionOrderDto createPartProductionOrder(CreatePartProductionOrderRequest request);

    ProductionOrderDto createAssemblyProductionOrder(CreateAssemblyProductionOrderRequest request);

    ProductionOrderDto stamp(StampDto stampDto) throws Exception;

    ProductionOrderDto removeStamp(String poCode, int stepNumber) throws Exception;

    List<StampDto> getStampsByCode(String code) throws Exception;

    List<TaskCenterPartCount> getTaskCenterMetrics() throws Exception;

    List<TaskCenterDto> getIdleTaskCenters() throws Exception;
}
