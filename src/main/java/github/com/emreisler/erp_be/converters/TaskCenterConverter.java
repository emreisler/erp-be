package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.entity.TaskCenter;

import java.util.ArrayList;
import java.util.List;

public class TaskCenterConverter {

    public static github.com.emreisler.erp_be.dto.TaskCenterDto toDto(TaskCenter taskCenter) {
        return new github.com.emreisler.erp_be.dto.TaskCenterDto(taskCenter.getUuid(), taskCenter.getNumber(), taskCenter.getName(), taskCenter.getInspection());
    }

    public static TaskCenter toEntity(github.com.emreisler.erp_be.dto.TaskCenterDto taskCenterDto) {
        return new TaskCenter(taskCenterDto.getUuid(), taskCenterDto.getNumber(), taskCenterDto.getName(), taskCenterDto.getInspection());
    }

    public static List<github.com.emreisler.erp_be.dto.TaskCenterDto> toDto(List<TaskCenter> taskCenter) {
        List<github.com.emreisler.erp_be.dto.TaskCenterDto> dtoList = new ArrayList<>();

        for (TaskCenter taskCenterDto : taskCenter) {
            dtoList.add(toDto(taskCenterDto));
        }
        return dtoList;
    }

    public static List<TaskCenter> toEntity(List<github.com.emreisler.erp_be.dto.TaskCenterDto> taskCenterDto) {
        List<TaskCenter> taskCenter = new ArrayList<>();
        for (github.com.emreisler.erp_be.dto.TaskCenterDto dto : taskCenterDto) {
            taskCenter.add(toEntity(dto));
        }
        return taskCenter;
    }
}


