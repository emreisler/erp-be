package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.application.dto.AssemblyDto;
import github.com.emreisler.erp_be.persistence.entity.Assembly;

public class AssemblyConverter {
    public static AssemblyDto toDto(Assembly assembly) {
        AssemblyDto dto = new AssemblyDto();
        dto.setName(assembly.getName());
        dto.setUuid(assembly.getUuid());
        dto.setNumber(assembly.getNumber());
        dto.setProjectCode(assembly.getProjectCode());
        dto.setCreatedAt(assembly.getCreatedAt());
        dto.setUpdatedAt(assembly.getUpdatedAt());
        dto.setOperationList(OperationConverter.toDto(assembly.getOperationList()));
        dto.setAttachedStocks(AttachStockConverter.toDto(assembly.getAttachedStocks()));
        dto.setAttachParts(AttachedPartConverter.toDto(assembly.getAttachedParts()));
        return dto;
    }

    public static Assembly toEntity(AssemblyDto dto) {
        Assembly assembly = new Assembly();
        assembly.setName(dto.getName());
        assembly.setUuid(dto.getUuid());
        assembly.setNumber(dto.getNumber());
        assembly.setProjectCode(dto.getProjectCode());
        assembly.setCreatedAt(dto.getCreatedAt());
        assembly.setUpdatedAt(dto.getUpdatedAt());
        assembly.setOperationList(OperationConverter.toEntity(dto.getOperationList()));
        assembly.setAttachedStocks(AttachStockConverter.toEntity(dto.getAttachedStocks()));
        assembly.setAttachedParts(AttachedPartConverter.toEntity(dto.getAttachParts()));
        return assembly;
    }

}
