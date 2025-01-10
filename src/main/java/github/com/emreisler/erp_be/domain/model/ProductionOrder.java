package github.com.emreisler.erp_be.domain.model;


import github.com.emreisler.erp_be.domain.enums.ProductionOrderStatus;
import github.com.emreisler.erp_be.persistence.entity.Assembly;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
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
 */
@Getter
@Setter
public class ProductionOrder {
    private UUID orderId;
    private OrderCode code;
    private Part part;
    private Assembly assembly;
    private int quantity;
    private ProductionOrderStatus status;
    private int currentStep;
    private int totalSteps;
    private List<Stamp> stampList;
    private int currentTaskCenter;
    private LocalDate endDate;

    public void stamp(Stamp stamp) {
        //if stamp operation number exist in operation list

    }


}
