package github.com.emreisler.erp_be.service.stock;

import github.com.emreisler.erp_be.converters.StockConverter;
import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.exception.NotFoundException;
import github.com.emreisler.erp_be.repository.StockRepository;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final Validator<StockDto> validator;


    public StockServiceImpl(StockRepository stockRepository, Validator<StockDto> validator) {
        this.stockRepository = stockRepository;
        this.validator = validator;
    }

    @Override
    public List<StockDto> GetAll() throws ErpRuntimeException {
        return stockRepository.findAll().
                stream().
                map(StockConverter::toDto).
                collect(Collectors.toList());
    }

    @Override
    public StockDto GetByCode(String code) throws ErpRuntimeException {
        return stockRepository.findByCode(code).
                map(StockConverter::toDto).
                orElseThrow(() -> new NotFoundException("Stock not found"));
    }

    @Override
    public StockDto GetByName(String name) throws ErpRuntimeException {
        return stockRepository.findByName(name).
                map(StockConverter::toDto).
                orElseThrow(() -> new NotFoundException("Stock not found"));
    }

    @Override
    public StockDto Create(StockDto stockDto) throws ErpRuntimeException {
        validator.validate(stockDto);
        stockDto.setUuid(UUID.randomUUID());
        stockDto.setCode("ST-" + stockDto.getUuid().toString().substring(0, 8).toUpperCase());
        return StockConverter.toDto(stockRepository.save(StockConverter.toEntity(stockDto)));
    }

    @Override
    public StockDto Update(String code, StockDto stockDto) throws ErpRuntimeException {

        validator.validate(stockDto);

        stockDto.setUuid(UUID.randomUUID());

        var updatedStock = stockRepository.findByCode(code).map(stock -> {
            stock.setCode(stockDto.getCode());
            stock.setName(stockDto.getName());
            stock.setQuantity(stockDto.getQuantity());
            stock.setUnit(stockDto.getUnit());
            stock.setUnitPrice(stockDto.getUnitPrice());
            stock.setUuid(stockDto.getUuid());
            return stockRepository.save(stock);
        }).orElseThrow(() -> new NotFoundException("Stock not found"));
        return StockConverter.toDto(updatedStock);
    }

    @Override
    @Transactional
    public void Delete(String code) throws ErpRuntimeException {
        stockRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Stock not found"));
        stockRepository.deleteByCode(code);
    }

}


