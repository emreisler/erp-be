package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.entity.TaskCenterDto;

import java.util.ArrayList;
import java.util.List;

public class TaskCenterConverter {

    public static github.com.emreisler.erp_be.dto.TaskCenterDto toDto(TaskCenterDto taskCenter) {
        return new github.com.emreisler.erp_be.dto.TaskCenterDto(taskCenter.getUuid(), taskCenter.getNumber(), taskCenter.getName(), taskCenter.getInspection());
    }

    public static TaskCenterDto toEntity(github.com.emreisler.erp_be.dto.TaskCenterDto taskCenterDto) {
        return new TaskCenterDto(taskCenterDto.getUuid(), taskCenterDto.getNumber(), taskCenterDto.getName(), taskCenterDto.getInspection());
    }

    public static List<github.com.emreisler.erp_be.dto.TaskCenterDto> toDto(List<TaskCenterDto> taskCenter) {
        List<github.com.emreisler.erp_be.dto.TaskCenterDto> dtoList = new ArrayList<>();

        for (TaskCenterDto taskCenterDto : taskCenter) {
            dtoList.add(toDto(taskCenterDto));
        }
        return dtoList;
    }

    public static List<TaskCenterDto> toEntity(List<github.com.emreisler.erp_be.dto.TaskCenterDto> taskCenterDto) {
        List<TaskCenterDto> taskCenter = new ArrayList<>();
        for (github.com.emreisler.erp_be.dto.TaskCenterDto dto : taskCenterDto) {
            taskCenter.add(toEntity(dto));
        }
        return taskCenter;
    }
}


