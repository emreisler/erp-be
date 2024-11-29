package github.com.emreisler.erp_be.dto;


import java.util.UUID;

public class TaskCenterDto {


    public TaskCenterDto(UUID uuid, int number, String name, Boolean isInspection) {
        this.uuid = uuid;
        this.number = number;
        this.name = name;
        this.isInspection = isInspection;
    }

    public TaskCenterDto() {}

    private UUID uuid;

    private int number;

    private String name;

    private boolean isInspection;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInspection() {
        return isInspection;
    }

    public void setInspection(Boolean inspection) {
        isInspection = inspection;
    }
}
