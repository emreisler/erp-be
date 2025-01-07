package github.com.emreisler.erp_be.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class CreateAssemblyProductionOrderRequest {
    private String assemblyNo;
    private int quantity;
    private LocalDate endDate;
}
