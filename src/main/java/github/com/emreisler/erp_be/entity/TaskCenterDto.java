package github.com.emreisler.erp_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TaskCenterDto {

    public TaskCenterDto(UUID uuid, int number, String name, Boolean isInspection) {
        this.uuid = uuid;
        this.number = number;
        this.name = name;
        this.isInspection = isInspection;
    }

    public TaskCenterDto() {}

    @Id
    private @GeneratedValue Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private int number;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isInspection;






    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = UUID.randomUUID();
    }
}
