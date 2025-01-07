package github.com.emreisler.erp_be.application.dto;

import github.com.emreisler.erp_be.domain.enums.ProductionOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class ProductionOrderDto {
    private UUID orderId;
    private String code;
    private String partNumber;
    private String assemblyNumber;
    private int quantity;
    private ProductionOrderStatus status;
    private int currentStep;
    private int totalSteps;
    private List<StampDto> stampList;
    private int currentTaskCenter;
    private LocalDate endDate;
}
