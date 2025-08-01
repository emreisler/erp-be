package github.com.emreisler.erp_be.domain.service.part;

import github.com.emreisler.erp_be.application.dto.AttachedStockDto;
import github.com.emreisler.erp_be.application.dto.OperationDto;
import github.com.emreisler.erp_be.application.dto.PartDto;
import github.com.emreisler.erp_be.application.dto.StockDto;
import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.converters.PartConverter;
import github.com.emreisler.erp_be.converters.StockConverter;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import github.com.emreisler.erp_be.domain.exception.ConflictException;
import github.com.emreisler.erp_be.domain.exception.NotFoundException;
import github.com.emreisler.erp_be.domain.validators.Validator;
import github.com.emreisler.erp_be.persistence.entity.Operation;
import github.com.emreisler.erp_be.persistence.entity.Part;
import github.com.emreisler.erp_be.persistence.repository.OperationRepository;
import github.com.emreisler.erp_be.persistence.repository.PartRepository;
import github.com.emreisler.erp_be.persistence.repository.StockRepository;
import github.com.emreisler.erp_be.persistence.repository.TaskCenterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PartServiceImpl implements PartService {


    private final PartRepository partRepository;
    private final OperationRepository operationRepository;
    private final TaskCenterRepository taskCenterRepository;
    private final StockRepository stockRepository;
    private final Validator<PartDto> partValidator;


    public PartServiceImpl(PartRepository partRepository, OperationRepository operationRepository, TaskCenterRepository taskCenterRepository, StockRepository stockRepository, Validator<PartDto> partValidator) {
        this.partRepository = partRepository;
        this.operationRepository = operationRepository;
        this.taskCenterRepository = taskCenterRepository;
        this.stockRepository = stockRepository;
        this.partValidator = partValidator;
    }

    @Override
    public List<PartDto> GetAll() throws Exception {
        return partRepository.findAllByOrderByUpdatedAtDesc()
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

        return partRepository.findByNumber(number)
                .map(PartConverter::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", number)));
    }

    @Override
    public List<OperationDto> GetOperations(String partNo) throws Exception {
        Part part = partRepository.findByNumber(partNo).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNo)));

        return part.getOperationList().stream().map(OperationConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStocks(String partNo) throws Exception {
        Part part = partRepository.findByNumber(partNo).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNo)));

        return part.getStocksList().stream().map(StockConverter::toDto).collect(Collectors.toList());
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

        operation.setOperationId(UUID.randomUUID());

        // todo check task center exist
        if (!taskCenterRepository.existsByNumber(operation.getTaskCenterNo())) {
            throw new BadRequestException(String.format("TaskCenter number %s not found", operation.getTaskCenterNo()));
        }

        if (operation.getStepNumber() <= 0) {
            throw new BadRequestException(String.format("Part operation number must be greater than zero", operation.getStepNumber()));
        }

        var part = partRepository.findByNumber(partNumber).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNumber)));

        Set<Integer> stepNumbers = new HashSet<>();

        for (Operation partOperation : part.getOperationList()) {
            stepNumbers.add(partOperation.getStepNumber());
        }

        if (stepNumbers.contains(operation.getStepNumber())) {
            throw new BadRequestException(String.format("Part operation number %s already exists", operation.getStepNumber()));
        }

        var operationEntity = OperationConverter.toEntity(operation);
        operationEntity.setPart(part);

        part.getOperationList().add(operationEntity);

        var updatedPart = partRepository.save(part);

        return PartConverter.toDto(updatedPart);

    }

    @Override
    public void updateOperation(UUID opID, OperationDto operation) throws Exception {
        var existingOp = operationRepository.findByOperationId(opID).orElseThrow(() -> new NotFoundException("Operation not found"));

        existingOp.setDescription(operation.getDescription());
        existingOp.setStepNumber(operation.getStepNumber());
        existingOp.setImageUrl(operation.getImageUrl());
        existingOp.setTaskCenterNo(operation.getTaskCenterNo());

        operationRepository.save(existingOp);
    }

    @Override
    public PartDto attachStock(String partNumber, AttachedStockDto attachedStockDto) throws Exception {
        var stock = stockRepository.findByCode(attachedStockDto.getCode()).orElseThrow(() -> new NotFoundException(String.format("Stock with code %s not found", attachedStockDto.getCode())));

        var part = partRepository.findByNumber(partNumber).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNumber)));

        var stocks = part.getStocksList();

        stocks.add(stock);

        try {
            return PartConverter.toDto(partRepository.save(part));
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    @Transactional
    public PartDto DeleteOperation(String partNumber, int stepNumber) throws Exception {
        var part = partRepository.findByNumber(partNumber).orElseThrow(() -> new NotFoundException(String.format("Part with number %s not found", partNumber)));

        var operations = part.getOperationList();
        if (operations.isEmpty()) {
            throw new NotFoundException(String.format("Part has no operations"));
        }

        Set<Integer> stepNumbers = part.getOperationList().stream()
                .map(Operation::getStepNumber)
                .collect(Collectors.toSet());
        if (!stepNumbers.contains(stepNumber)) {
            throw new BadRequestException(String.format("Part operation number %s does not exist", stepNumber));
        }

        operationRepository.deleteByPartIdAndStepNumber(part.getId(), stepNumber);

        return partRepository.findByNumber(partNumber).map(PartConverter::toDto).orElse(null);
    }

    @Override
    public void Delete(String partNumber) throws Exception {
        partRepository.deleteByNumber(partNumber);
    }


}
