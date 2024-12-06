package github.com.emreisler.erp_be.service.part;

import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.converters.PartConverter;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.entity.Operation;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.ConflictException;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.PartRepository;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PartServiceImpl implements PartService {


    private final PartRepository partRepository;
    private final Validator<PartDto> partValidator;


    public PartServiceImpl(PartRepository partRepository, Validator<PartDto> partValidator) {
        this.partRepository = partRepository;
        this.partValidator = partValidator;
    }

    @Override
    public List<PartDto> GetAll() throws Exception {
        return partRepository.findAll()
                .stream()
                .map(PartConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartDto> GetByProjectCode(String projectCode) throws Exception {
        return partRepository.findByProjectCode(projectCode)
                .stream()
                .map(PartConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PartDto GetByNumber(String number) throws Exception {
        var part = partRepository.findByNumber(number);
        System.out.println(part);
        return partRepository.findByNumber(number)
                .map(PartConverter::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", number)));
    }

    @Override
    public PartDto GetByName(String name) throws Exception {
        return partRepository.findByName(name)
                .map(PartConverter::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", name)));
    }

    @Override
    public PartDto Create(PartDto partDto) throws Exception {
        partValidator.validate(partDto);

        partRepository.findByNumber(partDto.getNumber()).ifPresent(part -> {
            throw new ConflictException(String.format("Part with number %s already exists", part.getNumber()));
        });

        partDto.setUuid(UUID.randomUUID());
        return PartConverter.toDto(partRepository.save(PartConverter.toEntity(partDto)));
    }

    @Override
    public PartDto AttachOperation(String partNumber, OperationDto operation) throws Exception {
        if (operation.getSepNumber() <= 0) {
            throw new BadRequestException(String.format("Part operation number must be greater than zero", operation.getSepNumber()));
        }

        var part = partRepository.findByNumber(partNumber).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNumber)));

        Set<Integer> stepNumbers = new HashSet<>();

        for (Operation partOperation : part.getOperationList()) {
            if (stepNumbers.contains(partOperation.getSepNumber())) {
                throw new ConflictException(String.format("Part operation number %s already exists", part.getNumber()));
            }
            stepNumbers.add(operation.getSepNumber());
        }

        var operationEntity = OperationConverter.toEntity(operation);
        operationEntity.setPart(part);

        part.getOperationList().add(operationEntity);

        var updatedPart = partRepository.save(part);

        return PartConverter.toDto(updatedPart);

    }

    @Override
    public void Delete(String partNumber) throws Exception {

        partRepository.deleteByNumber(partNumber);
    }

}
