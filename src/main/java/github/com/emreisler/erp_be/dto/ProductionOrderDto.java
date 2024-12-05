package github.com.emreisler.erp_be.dto;

import github.com.emreisler.erp_be.enums.ProductionOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class ProductionOrderDto {
    private UUID orderId;
    private String code;
    private String partNumber;
    private int quantity;
    private ProductionOrderStatus status;
    private int currentStep;
    private int totalSteps;
    private int currentTaskCenter;
    private LocalDate endDate;
}
