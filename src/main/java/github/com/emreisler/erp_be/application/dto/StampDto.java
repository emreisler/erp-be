package github.com.emreisler.erp_be.application.dto;

public class StampDto {
    private String userEmail;
    private String productionOrderCode;
    private int stepNumber;

    public StampDto(String userEmail, String productionOrderCode, int stepNumber) {
        this.userEmail = userEmail;
        this.productionOrderCode = productionOrderCode;
        this.stepNumber = stepNumber;
    }

    public StampDto() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getProductionOrderCode() {
        return productionOrderCode;
    }

    public void setProductionOrderCode(String productionOrderCode) {
        this.productionOrderCode = productionOrderCode;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
