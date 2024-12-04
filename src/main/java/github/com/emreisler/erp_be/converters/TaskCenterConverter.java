package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.TaskCenterDto;
import github.com.emreisler.erp_be.entity.TaskCenter;

import java.util.ArrayList;
import java.util.List;

public class TaskCenterConverter {

    public static TaskCenterDto toDto(TaskCenter taskCenter) {
        return new TaskCenterDto(taskCenter.getUuid(), taskCenter.getNumber(), taskCenter.getName(), taskCenter.getIsInspection());
    }

    public static TaskCenter toEntity(TaskCenterDto taskCenterDto) {
        return new TaskCenter(taskCenterDto.getUuid(), taskCenterDto.getNumber(), taskCenterDto.getName(), taskCenterDto.getIsInspection());
    }

    public static List<TaskCenterDto> toDto(List<TaskCenter> taskCenter) {
        List<TaskCenterDto> dtoList = new ArrayList<>();

        for (TaskCenter taskCenterDto : taskCenter) {
            dtoList.add(toDto(taskCenterDto));
        }
        return dtoList;
    }

    public static List<TaskCenter> toEntity(List<TaskCenterDto> taskCenterDto) {
        List<TaskCenter> taskCenter = new ArrayList<>();
        for (TaskCenterDto dto : taskCenterDto) {
            taskCenter.add(toEntity(dto));
        }
        return taskCenter;
    }
}


