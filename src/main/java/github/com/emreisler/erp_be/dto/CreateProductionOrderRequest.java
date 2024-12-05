package github.com.emreisler.erp_be.dto;

import java.time.LocalDate;

public class CreateProductionOrderRequest {
    private String partNo;
    private int quantity;
    private LocalDate endDate; // If you also need the end date

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
