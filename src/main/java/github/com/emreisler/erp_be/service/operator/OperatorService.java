package github.com.emreisler.erp_be.service.operator;

import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.dto.StampDto;

public interface OperatorService {
    ProductionOrderDto stamp(StampDto stampDto) throws Exception;
}
