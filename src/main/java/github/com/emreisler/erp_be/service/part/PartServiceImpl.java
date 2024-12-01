package github.com.emreisler.erp_be.service.part;

import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.converters.PartConverter;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.entity.Operation;
import github.com.emreisler.erp_be.exception.DuplicateOperationStepException;
import github.com.emreisler.erp_be.exception.PartConflictException;
import github.com.emreisler.erp_be.exception.PartNotFoundException;
import github.com.emreisler.erp_be.exception.StepNumberNotPositiveException;
import github.com.emreisler.erp_be.repository.PartRepository;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
                .orElseThrow(PartNotFoundException::new);
    }

    @Override
    public PartDto GetByName(String name) throws Exception {
        return partRepository.findByName(name)
                .map(PartConverter::toDto)
                .orElseThrow(PartNotFoundException::new);
    }

    @Override
    public PartDto Create(PartDto partDto) throws Exception {
        partValidator.validate(partDto);

        partRepository.findByNumber(partDto.getNumber()).ifPresent(part -> {
            throw new PartConflictException();
        });

        partDto.setUuid(UUID.randomUUID());
        return PartConverter.toDto(partRepository.save(PartConverter.toEntity(partDto)));
    }

    @Override
    public PartDto AttachOperation(String partNumber, List<OperationDto> operations) throws Exception {

        Set<Integer> stepNumbers = new HashSet<>();

        for (OperationDto operation : operations) {
            if (operation.getSepNumber() <= 0) {
                throw new StepNumberNotPositiveException();
            }
            if (stepNumbers.contains(operation.getSepNumber())) {
                throw new DuplicateOperationStepException(String.format("Duplicate operation step number %d", operation.getSepNumber()));
            }
            stepNumbers.add(operation.getSepNumber());
        }

        var part = partRepository.findByNumber(partNumber).orElseThrow(PartNotFoundException::new);

        for (Operation operation : part.getOperationList()) {
            if (stepNumbers.contains(operation.getSepNumber())) {
                throw new DuplicateOperationStepException(String.format("Part operation already has step number %d", operation.getSepNumber()));
            }
        }

        var newOperations = operations.stream()
                .map(OperationConverter::toEntity)
                .peek(operation -> operation.setPart(part))
                .toList();


        List<Operation> updatedOperations = Stream.concat(part.getOperationList().stream(), newOperations.stream()).collect(Collectors.toList());

        part.setOperationList(updatedOperations);

        var updatedPart = partRepository.save(part);

        return PartConverter.toDto(updatedPart);

    }

    @Override
    public void Delete(String partNumber) throws Exception {
        partRepository.deleteByNumber(partNumber);
    }

}
