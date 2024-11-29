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
                stock.uuid,
                stock.name,
                stock.quantity,
                stock.unitPrice,
                stock.unit,
                stock.code
        );
    }

    public static Stock toEntity(StockDto stockDto) {
        if (stockDto == null) {
            return null;
        }
        return new Stock(
                stockDto.uuid,
                stockDto.name,
                stockDto.quantity,
                stockDto.unitPrice,
                stockDto.unit,
                stockDto.code
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
