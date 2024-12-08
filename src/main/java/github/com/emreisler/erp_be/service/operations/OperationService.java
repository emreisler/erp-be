package github.com.emreisler.erp_be.service.operations;

import github.com.emreisler.erp_be.dto.OperationDto;

import java.util.List;

public interface OperationService {

    List<OperationDto> getByPartNumber(String partNo) throws Exception;
}
