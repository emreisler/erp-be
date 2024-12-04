package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.entity.Part;

import java.util.List;
import java.util.stream.Collectors;

public class PartConverter {

    public static PartDto toDto(Part part) {
        if (part == null) {
            return null;
        }

        return new PartDto(
                part.getUuid(),
                part.getNumber(),
                part.getName(),
                part.getProjectCode(),
                part.getCategory(),
                StockConverter.toDto(part.getStocksList()),
                OperationConverter.toDto(part.getOperationList()) // Assuming there's an OperationConverter
        );
    }

    public static Part toEntity(PartDto partDto) {
        if (partDto == null) {
            return null;
        }

        Part part = new Part();
        part.setUuid(partDto.getUuid());
        part.setNumber(partDto.getNumber());
        part.setName(partDto.getName());
        part.setProjectCode(partDto.getProjectCode()); // Assuming there's a ProjectConverter
        part.setCategory(partDto.getCategory());
        part.setStocksList(StockConverter.toEntity(partDto.getStocksList()));
        part.setOperationList(OperationConverter.toEntity(partDto.getOperationList())); // Assuming there's an OperationConverter
        return part;
    }

    public static List<PartDto> toDto(List<Part> parts) {
        if (parts == null) {
            return null;
        }
        return parts.stream().map(PartConverter::toDto).collect(Collectors.toList());
    }

    public static List<Part> toEntity(List<PartDto> partDtos) {
        if (partDtos == null) {
            return null;
        }
        return partDtos.stream().map(PartConverter::toEntity).collect(Collectors.toList());
    }
}
