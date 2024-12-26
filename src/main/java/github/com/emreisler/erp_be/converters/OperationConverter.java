package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.entity.Operation;

import java.util.List;
import java.util.stream.Collectors;

public class OperationConverter {

    public static OperationDto toDto(Operation operation) {
        if (operation == null) {
            return null;
        }

        var operationDto = new OperationDto();
        operationDto.setStepNumber(operation.getStepNumber());
        operationDto.setDescription(operation.getDescription());
        operationDto.setImageUrl(operation.getImageUrl());
        operationDto.setTaskCenterNo(operation.getTaskCenterNo());
        operationDto.setOperationId(operation.getOperationId());
        if (operation.getPart() != null) {
            operationDto.setPartNumber(operation.getPart().getNumber());
        }
        if (operation.getAssembly() != null) {
            operationDto.setAssemblyNumber(operation.getAssembly().getNumber());
        }

        return operationDto;
    }

    public static Operation toEntity(OperationDto operationDto) {
        if (operationDto == null) {
            return null;
        }

        Operation operation = new Operation();
        operation.setOperationId(operationDto.getOperationId());
        operation.setStepNumber(operationDto.getStepNumber());
        operation.setDescription(operationDto.getDescription());
        operation.setImageUrl(operationDto.getImageUrl());
        operation.setTaskCenterNo(operationDto.getTaskCenterNo());
        return operation;
    }

    public static List<OperationDto> toDto(List<Operation> operations) {
        if (operations == null) {
            return null;
        }
        return operations.stream().map(OperationConverter::toDto).collect(Collectors.toList());
    }

    public static List<Operation> toEntity(List<OperationDto> operationDtos) {
        if (operationDtos == null) {
            return null;
        }
        return operationDtos.stream().map(OperationConverter::toEntity).collect(Collectors.toList());
    }
}
