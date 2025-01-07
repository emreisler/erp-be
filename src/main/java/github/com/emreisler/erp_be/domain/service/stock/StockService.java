package github.com.emreisler.erp_be.domain.service.stock;

import github.com.emreisler.erp_be.application.dto.StockDto;

import java.util.List;

public interface StockService {

    List<StockDto> GetAll() throws Exception;

    StockDto GetByCode(String code) throws Exception;

    StockDto GetByName(String name) throws Exception;

    StockDto Create(StockDto stockDto) throws Exception;

    StockDto Update(String code, StockDto stockDto) throws Exception;

    void Delete(String code) throws Exception;
}
