package github.com.emreisler.erp_be.service.part;

import github.com.emreisler.erp_be.dto.AttachStockRequest;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.dto.StockDto;

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

    PartDto attachStock(String partNumber, AttachStockRequest attachStockRequest) throws Exception;

    PartDto DeleteOperation(String partNumber, int stepNumber) throws Exception;

    void Delete(String partNumber) throws Exception;
}
