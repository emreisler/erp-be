package github.com.emreisler.erp_be.service.stamp;

import github.com.emreisler.erp_be.converters.StampConverter;
import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.repository.StampRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StampServiceImpl implements StampService {

    private final StampRepository stampRepository;

    StampServiceImpl(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

    @Override
    @Transactional
    public StampDto stamp(StampDto stampDto) throws ErpRuntimeException {
        if (stampRepository.existsByPoCodeAndStepNumber(stampDto.getProductionOrderCode(), stampDto.getStepNumber())) {
            throw new ErpRuntimeException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "step is already stamped");
        }
        return StampConverter.toDto(stampRepository.save(StampConverter.toEntity(stampDto)));
    }

    @Override
    public List<StampDto> getByPoCode(String code) throws ErpRuntimeException {
        return stampRepository.findByPoCode(code).stream().map(StampConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean isStamped(String poCode, int stepNumber) throws ErpRuntimeException {
        return stampRepository.existsByPoCodeAndStepNumber(poCode, stepNumber);
    }
}
