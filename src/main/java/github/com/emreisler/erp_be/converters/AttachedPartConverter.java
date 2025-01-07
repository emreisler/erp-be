package github.com.emreisler.erp_be.converters;


import github.com.emreisler.erp_be.application.dto.AttachPartDto;
import github.com.emreisler.erp_be.persistence.entity.AttachedPart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttachedPartConverter {
    public static AttachPartDto toDto(AttachedPart attachedPart) {
        AttachPartDto dto = new AttachPartDto();
        dto.setPartNumber(attachedPart.getPartNumber());
        dto.setQuantity(attachedPart.getQuantity());
        return dto;
    }

    public static AttachedPart toEntity(AttachPartDto dto) {
        AttachedPart attachedPart = new AttachedPart();
        attachedPart.setPartNumber(dto.getPartNumber());
        attachedPart.setQuantity(dto.getQuantity());
        return attachedPart;
    }

    public static List<AttachPartDto> toDto(List<AttachedPart> attachedParts) {
        if (attachedParts == null || attachedParts.isEmpty()) {
            return new ArrayList<>();
        }
        return attachedParts.stream().map(AttachedPartConverter::toDto).collect(Collectors.toList());
    }

    public static List<AttachedPart> toEntity(List<AttachPartDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }
        return dtos.stream().map(AttachedPartConverter::toEntity).collect(Collectors.toList());
    }

}

