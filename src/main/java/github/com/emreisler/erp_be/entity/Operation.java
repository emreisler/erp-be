package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    @NotNull
    private Part part;

    @Column(nullable = false)
    private int sepNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String imageUrl;


    @Column(nullable = false)
    private int taskCenterNo;


    public Operation(Long id, Part part, int sepNumber, String description, String imageUrl, int taskCenterNo) {
        this.id = id;
        this.part = part;
        this.sepNumber = sepNumber;
        this.description = description;
        this.imageUrl = imageUrl;
        this.taskCenterNo = taskCenterNo;
    }

    public Operation() {
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getSepNumber() {
        return sepNumber;
    }

    public void setSepNumber(int sepNumber) {
        this.sepNumber = sepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTaskCenterNo() {
        return taskCenterNo;
    }

    public void setTaskCenterNo(int taskCenterNo) {
        this.taskCenterNo = taskCenterNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
