package github.com.emreisler.erp_be.converters;


import github.com.emreisler.erp_be.application.dto.AttachedStockDto;
import github.com.emreisler.erp_be.persistence.entity.AttachedStock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttachStockConverter {
    public static AttachedStockDto toDto(AttachedStock attachedStock) {
        var dto = new AttachedStockDto();
        dto.setName(attachedStock.getName());
        dto.setQuantity(attachedStock.getQuantity());
        dto.setCode(attachedStock.getCode());
        return dto;
    }

    public static AttachedStock toEntity(AttachedStockDto dto) {
        var attachedStock = new AttachedStock();
        attachedStock.setName(dto.getName());
        attachedStock.setQuantity(dto.getQuantity());
        attachedStock.setCode(dto.getCode());
        return attachedStock;
    }

    public static List<AttachedStockDto> toDto(List<AttachedStock> attachedStocks) {
        if (attachedStocks == null) {
            return new ArrayList<>();
        }
        return attachedStocks.stream().map(AttachStockConverter::toDto).collect(Collectors.toList());
    }

    public static List<AttachedStock> toEntity(List<AttachedStockDto> dtos) {

        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(AttachStockConverter::toEntity).collect(Collectors.toList());
    }

}

