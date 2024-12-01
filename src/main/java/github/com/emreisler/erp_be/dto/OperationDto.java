package github.com.emreisler.erp_be.dto;

public class OperationDto {

    private String partNumber;
    private int sepNumber;
    private String description;
    private String imageUrl;
    private int taskCenterNo;

    public OperationDto(String partNumber, int sepNumber, String description, String imageUrl, int taskCenterNo) {
        this.partNumber = partNumber;
        this.sepNumber = sepNumber;
        this.description = description;
        this.imageUrl = imageUrl;
        this.taskCenterNo = taskCenterNo;
    }

    public OperationDto() {
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
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
}
