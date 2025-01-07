package github.com.emreisler.erp_be.domain.service.part;

import github.com.emreisler.erp_be.application.dto.AttachedStockDto;
import github.com.emreisler.erp_be.application.dto.OperationDto;
import github.com.emreisler.erp_be.application.dto.PartDto;
import github.com.emreisler.erp_be.application.dto.StockDto;

import java.util.List;

public interface PartService {

    List<PartDto> GetAll() throws Exception;

    List<PartDto> GetByProjectCode(String projectCode) throws Exception;

    PartDto GetByNumber(String number) throws Exception;

    List<OperationDto> GetOperations(String partNo) throws Exception;

    List<StockDto> getStocks(String partNo) throws Exception;

    PartDto GetByName(String name) throws Exception;

    PartDto Create(PartDto partDto) throws Exception;

    PartDto AttachOperation(String partNumber, OperationDto operation) throws Exception;

    PartDto attachStock(String partNumber, AttachedStockDto attachedStockDto) throws Exception;

    PartDto DeleteOperation(String partNumber, int stepNumber) throws Exception;

    void Delete(String partNumber) throws Exception;
}
