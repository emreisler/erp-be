package github.com.emreisler.erp_be.service.part;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;

import java.util.List;

public interface PartService {

    List<PartDto> GetAll() throws Exception;

    List<PartDto> GetByProjectCode(String projectCode) throws Exception;

    PartDto GetByNumber(String number) throws Exception;

    PartDto GetByName(String name) throws Exception;

    PartDto Create(PartDto partDto) throws Exception;

    PartDto AttachOperation(String partNumber, List<OperationDto> operations) throws Exception;

    void Delete(String partNumber) throws Exception;
}
