package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.entity.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockConverter {

    public static StockDto toDto(Stock stock) {
        if (stock == null) {
            return null;
        }
        return new StockDto(
                stock.getUuid(),
                stock.getName(),
                stock.getQuantity(),
                stock.getUnitPrice(),
                stock.getUnit(),
                stock.getCode()
        );
    }

    public static Stock toEntity(StockDto stockDto) {
        if (stockDto == null) {
            return null;
        }
        return new Stock(
                stockDto.getUuid(),
                stockDto.getName(),
                stockDto.getQuantity(),
                stockDto.getUnitPrice(),
                stockDto.getUnit(),
                stockDto.getCode()
        );
    }

    public static List<StockDto> toDto(List<Stock> stocks) {
        if (stocks == null) {
            return null;
        }
        List<StockDto> dtoList = new ArrayList<>();
        for (Stock stock : stocks) {
            dtoList.add(toDto(stock));
        }
        return dtoList;
    }

    public static List<Stock> toEntity(List<StockDto> stockDtos) {
        if (stockDtos == null) {
            return null;
        }
        List<Stock> entityList = new ArrayList<>();
        for (StockDto dto : stockDtos) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
