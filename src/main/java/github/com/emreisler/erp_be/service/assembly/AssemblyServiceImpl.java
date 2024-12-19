package github.com.emreisler.erp_be.service.assembly;

import github.com.emreisler.erp_be.converters.AssemblyConverter;
import github.com.emreisler.erp_be.dto.AssemblyDto;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.repository.AssemblyRepository;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssemblyServiceImpl implements AssemblyService {

    private final AssemblyRepository assemblyRepository;
    private final Validator<AssemblyDto> validator;

    public AssemblyServiceImpl(AssemblyRepository assemblyRepository, Validator<AssemblyDto> validator) {
        this.assemblyRepository = assemblyRepository;
        this.validator = validator;
    }

    @Override
    public List<AssemblyDto> getAll() throws Exception {
        return assemblyRepository.findAll().stream().map(AssemblyConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AssemblyDto> getByProject(String projectCode) throws Exception {
        return assemblyRepository.findByProjectCode(projectCode).stream().map(AssemblyConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public AssemblyDto getByNumber(String assemblyNo) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto Create(AssemblyDto assemblyDto) throws Exception {
        validator.validate(assemblyDto);
        assemblyDto.setUuid(UUID.randomUUID());
        return AssemblyConverter.toDto(assemblyRepository.save(AssemblyConverter.toEntity(assemblyDto)));
    }

    @Override
    public AssemblyDto Update(AssemblyDto assemblyDto) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto Delete(String assemblyNo) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto AttachPart(String assemblyNo, PartDto partDto) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto RemovePart(String assemblyNo, PartDto partDto) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto AddOperation(String assemblyNo, OperationDto operationDto) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto RemoveOperation(String assemblyNo, int operationNumber) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto GetOperations(String assemblyNo) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto AttachMaterial(String assemblyNo, String materialNo) throws Exception {
        return null;
    }
}
