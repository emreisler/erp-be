package github.com.emreisler.erp_be.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskCenterPartCount {
    TaskCenterDto taskCenter;
    List<PartDto> parts;
    int totalPartQuantity;

    public TaskCenterPartCount(TaskCenterDto taskCenter) {
        this.taskCenter = taskCenter;
        this.parts = new ArrayList<>();
    }

    public void addPart(PartDto partDto) {
        parts.add(partDto);
    }

    public void increaseQuantity(int qty) {
        totalPartQuantity += qty;
    }

}


