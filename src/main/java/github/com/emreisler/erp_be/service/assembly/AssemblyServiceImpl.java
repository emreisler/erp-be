package github.com.emreisler.erp_be.service.assembly;

import github.com.emreisler.erp_be.converters.AssemblyConverter;
import github.com.emreisler.erp_be.converters.AttachStockConverter;
import github.com.emreisler.erp_be.converters.AttachedPartConverter;
import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.dto.*;
import github.com.emreisler.erp_be.entity.Operation;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.AssemblyRepository;
import github.com.emreisler.erp_be.repository.OperationRepository;
import github.com.emreisler.erp_be.repository.PartRepository;
import github.com.emreisler.erp_be.repository.StockRepository;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssemblyServiceImpl implements AssemblyService {

    private final AssemblyRepository assemblyRepository;
    private final StockRepository stockRepository;
    private final OperationRepository operationRepository;
    private final PartRepository partRepository;
    private final Validator<AssemblyDto> validator;

    public AssemblyServiceImpl(AssemblyRepository assemblyRepository, StockRepository stockRepository, PartRepository partRepository, OperationRepository operationRepository, Validator<AssemblyDto> validator) {
        this.assemblyRepository = assemblyRepository;
        this.stockRepository = stockRepository;
        this.partRepository = partRepository;
        this.operationRepository = operationRepository;
        this.validator = validator;
    }

    @Override
    public List<AssemblyDto> getAll() throws Exception {
        return assemblyRepository.findAllByOrderByUpdatedAtDesc().stream().map(AssemblyConverter::toDto).collect(Collectors.toList());
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
    public AssemblyDto attachPart(String assemblyNo, AttachPartDto attachPartDto) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("Assembly with number %s not found", assemblyNo)));

        if (attachPartDto.getPartNumber().isEmpty()) {
            throw new BadRequestException(String.format("Part number %s not found", attachPartDto.getPartNumber()));
        }
        if (attachPartDto.getQuantity() <= 0) {
            throw new BadRequestException(String.format("Part number %s must be greater than zero", attachPartDto.getPartNumber()));
        }
        var attachedParts = assembly.getAttachedParts();
        attachedParts.add(AttachedPartConverter.toEntity(attachPartDto));
        assembly.setAttachedParts(attachedParts);
        var updatedAssembly = assemblyRepository.save(assembly);
        return AssemblyConverter.toDto(updatedAssembly);
    }

    @Override
    public List<AttachPartDto> getPartsByAssemblyNo(String assemblyNo) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNo)));

        return AttachedPartConverter.toDto(assembly.getAttachedParts());
    }

    @Override
    public AssemblyDto AttachOperation(String assemblyNo, OperationDto operationDto) throws Exception {

        operationDto.setOperationId(UUID.randomUUID());

        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("Operation with number %s not found", assemblyNo)));

        Set<Integer> stepNumbers = new HashSet<>();

        for (Operation partOperation : assembly.getOperationList()) {
            stepNumbers.add(partOperation.getStepNumber());
        }

        if (stepNumbers.contains(operationDto.getStepNumber())) {
            throw new BadRequestException(String.format("Part operation number %s already exists", operationDto.getStepNumber()));
        }

        var operationEntity = OperationConverter.toEntity(operationDto);
        operationEntity.setAssembly(assembly);

        assembly.getOperationList().add(operationEntity);

        var updatedAssembly = assemblyRepository.save(assembly);

        return AssemblyConverter.toDto(updatedAssembly);
    }

    @Override
    public AssemblyDto RemoveOperation(String assemblyNo, int operationNumber) throws Exception {
        return null;
    }

    @Override
    public List<OperationDto> getOperations(String assemblyNo) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNo)));

        return assembly.getOperationList().stream().map(OperationConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public AssemblyDto attachStock(String assemblyNo, AttachedStockDto attachedStockDto) throws Exception {
        if (attachedStockDto.getCode().isEmpty()) {
            throw new BadRequestException(String.format("Stock code %s not found", attachedStockDto.getCode()));
        }
        if (attachedStockDto.getQuantity() <= 0) {
            throw new BadRequestException(String.format("Stock number %s must be greater than zero", attachedStockDto.getQuantity()));
        }

        var stock = stockRepository.findByCode(attachedStockDto.getCode()).orElseThrow(() -> new NotFoundException(String.format("Stock with code %s not found", attachedStockDto.getCode())));

        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("Assembly with number %s not found", assemblyNo)));

        var stocks = assembly.getAttachedStocks();

        var attachedStockEntity = AttachStockConverter.toEntity(attachedStockDto);
        attachedStockEntity.setName(attachedStockDto.getName());

        stocks.add(attachedStockEntity);
        assembly.setAttachedStocks(stocks);
        try {
            return AssemblyConverter.toDto(assemblyRepository.save(assembly));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AttachedStockDto> getStocks(String assemblyNo) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNo)));

        return AttachStockConverter.toDto(assembly.getAttachedStocks());
    }

    @Override
    public AssemblyDto GetOperations(String assemblyNo) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto AttachMaterial(String assemblyNo, String materialNo) throws Exception {
        return null;
    }

    @Override
    public AssemblyDto updateOperation(String assemblyNumber, OperationDto operationDto) {
        var assembly = assemblyRepository.findByNumber(assemblyNumber).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNumber)));

        if (assembly.getOperationList().isEmpty()) {
            throw new BadRequestException(String.format("No operations found with number %s", assemblyNumber));
        }

        var operationEntity = operationRepository.findByOperationId(operationDto.getOperationId()).orElseThrow(() -> new NotFoundException(String.format("Operation with number %s not found", operationDto.getStepNumber())));


        for (Operation operation : assembly.getOperationList()) {
            if (!operation.getOperationId().equals(operationDto.getOperationId()) && operation.getStepNumber() == operationDto.getStepNumber()) {
                throw new BadRequestException(String.format("Operation with number %s already exists", operationDto.getStepNumber()));
            }
        }
        operationEntity.setStepNumber(operationDto.getStepNumber());
        operationEntity.setDescription(operationDto.getDescription());
        operationEntity.setImageUrl(operationDto.getImageUrl());
        operationEntity.setTaskCenterNo(operationDto.getTaskCenterNo());

        try {
            operationRepository.save(operationEntity);
            return AssemblyConverter.toDto(assemblyRepository.findByNumber(assemblyNumber).orElseThrow(() -> new NotFoundException(String.format("Operation with number %s not found", operationDto.getStepNumber()))));
        } catch (Exception e) {
            throw e;
        }
    }
}
