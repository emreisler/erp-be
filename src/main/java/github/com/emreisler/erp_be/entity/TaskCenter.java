package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@Entity
public class TaskCenter {

    public TaskCenter(UUID uuid, int number, String name, Boolean isInspection) {
        this.uuid = uuid;
        this.number = number;
        this.name = name;
        this.isInspection = isInspection;
    }

    public TaskCenter() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private int number;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isInspection;


    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date modifiedAt;


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

    public Boolean getIsInspection() {
        return isInspection;
    }

    public void setIsInspection(Boolean inspection) {
        isInspection = inspection;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = UUID.randomUUID();
    }
}
