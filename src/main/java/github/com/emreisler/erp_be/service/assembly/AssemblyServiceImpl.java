package github.com.emreisler.erp_be.service.assembly;

import github.com.emreisler.erp_be.converters.AssemblyConverter;
import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.converters.PartConverter;
import github.com.emreisler.erp_be.converters.StockConverter;
import github.com.emreisler.erp_be.dto.*;
import github.com.emreisler.erp_be.entity.Operation;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.AssemblyRepository;
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
    private final PartRepository partRepository;
    private final Validator<AssemblyDto> validator;

    public AssemblyServiceImpl(AssemblyRepository assemblyRepository, StockRepository stockRepository, PartRepository partRepository, Validator<AssemblyDto> validator) {
        this.assemblyRepository = assemblyRepository;
        this.stockRepository = stockRepository;
        this.partRepository = partRepository;
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
    public List<PartDto> getPartsByAssemblyNo(String assemblyNo) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNo)));

        return assembly.getPartList().stream().map(PartConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public AssemblyDto AttachOperation(String assemblyNo, OperationDto operationDto) throws Exception {


        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("Operation with number %s not found", assemblyNo)));

        Set<Integer> stepNumbers = new HashSet<>();

        for (Operation partOperation : assembly.getOperationList()) {
            stepNumbers.add(partOperation.getSepNumber());
        }

        if (stepNumbers.contains(operationDto.getSepNumber())) {
            throw new BadRequestException(String.format("Part operation number %s already exists", operationDto.getSepNumber()));
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
    public AssemblyDto attachStock(String assemblyNo, AttachStockRequest attachStockRequest) throws Exception {
        var stock = stockRepository.findByCode(attachStockRequest.getCode()).orElseThrow(() -> new NotFoundException(String.format("Stock with code %s not found", attachStockRequest.getCode())));

        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("Assembly with number %s not found", assemblyNo)));

        var stocks = assembly.getStockList();

        stocks.add(stock);

        try {
            return AssemblyConverter.toDto(assemblyRepository.save(assembly));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<StockDto> getStocks(String assemblyNo) throws Exception {
        var assembly = assemblyRepository.findByNumber(assemblyNo).orElseThrow(() -> new NotFoundException(String.format("No assembly found with number %s", assemblyNo)));

        return assembly.getStockList().stream().map(StockConverter::toDto).collect(Collectors.toList());
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
