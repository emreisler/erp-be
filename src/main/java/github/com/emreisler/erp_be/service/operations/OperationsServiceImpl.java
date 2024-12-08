package github.com.emreisler.erp_be.service.operations;

import github.com.emreisler.erp_be.converters.OperationConverter;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.OperationsRepository;
import github.com.emreisler.erp_be.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationsServiceImpl implements OperationService {

    private final OperationsRepository operationsRepository;
    private final PartRepository partRepository;

    public OperationsServiceImpl(OperationsRepository operationsRepository, PartRepository partRepository) {
        this.operationsRepository = operationsRepository;
        this.partRepository = partRepository;
    }

    @Override
    public List<OperationDto> getByPartNumber(String partNo) throws Exception {

        var part = partRepository.findByNumber(partNo).orElseThrow(() -> new NotFoundException("part not found"));

        return operationsRepository.findByPartId(part.getId()).stream()
                .map(OperationConverter::toDto)
                .toList();
    }
}
