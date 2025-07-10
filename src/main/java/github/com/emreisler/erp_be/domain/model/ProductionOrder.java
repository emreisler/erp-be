package github.com.emreisler.erp_be.domain.model;


import github.com.emreisler.erp_be.domain.enums.ProductionOrderStatus;
import github.com.emreisler.erp_be.persistence.entity.Assembly;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//Aggregate root
public class ProductionOrder {
    private UUID orderId;
    private OrderCode code;
    private Part part;
    private Assembly assembly;
    private int quantity; //todo value object
    private ProductionOrderStatus status;
    private List<Stamp> stampList;
    private List<Operation> operations;
    private int currentStep;
    private int totalSteps;

    private int currentTaskCenter;
    private LocalDate endDate;

    public void stamp(Stamp stamp) {
        //if stamp operation number exist in operation list

    }


}
