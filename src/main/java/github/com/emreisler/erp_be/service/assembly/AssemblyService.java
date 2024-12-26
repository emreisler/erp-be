package github.com.emreisler.erp_be.service.assembly;

import github.com.emreisler.erp_be.dto.*;

import java.util.List;

public interface AssemblyService {

    List<AssemblyDto> getAll() throws Exception;

    List<AssemblyDto> getByProject(String projectCode) throws Exception;

    AssemblyDto getByNumber(String assemblyNo) throws Exception;

    AssemblyDto Create(AssemblyDto assemblyDto) throws Exception;

    AssemblyDto Update(AssemblyDto assemblyDto) throws Exception;

    AssemblyDto Delete(String assemblyNo) throws Exception;

    AssemblyDto AttachPart(String assemblyNo, PartDto partDto) throws Exception;

    AssemblyDto RemovePart(String assemblyNo, PartDto partDto) throws Exception;

    AssemblyDto attachPart(String assemblyNo, AttachPartDto attachPartDto) throws Exception;

    List<AttachPartDto> getPartsByAssemblyNo(String assemblyNo) throws Exception;

    AssemblyDto AttachOperation(String assemblyNo, OperationDto operationDto) throws Exception;

    AssemblyDto RemoveOperation(String assemblyNo, int operationNumber) throws Exception;

    List<OperationDto> getOperations(String assemblyNo) throws Exception;

    AssemblyDto attachStock(String assemblyNo, AttachedStockDto attachedStockDto) throws Exception;

    List<AttachedStockDto> getStocks(String assemblyNo) throws Exception;

    AssemblyDto GetOperations(String assemblyNo) throws Exception;

    AssemblyDto AttachMaterial(String assemblyNo, String materialNo) throws Exception;

    AssemblyDto updateOperation(String assemblyNumber, OperationDto operationDto);
}
