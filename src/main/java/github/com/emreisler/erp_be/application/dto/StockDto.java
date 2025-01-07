package github.com.emreisler.erp_be.application.dto;

import github.com.emreisler.erp_be.domain.enums.Currency;
import github.com.emreisler.erp_be.domain.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDto {

    private UUID uuid;
    private String name;
    private float quantity;
    private float unitPrice;
    private Currency currency;
    private Unit unit;
    private String code;
}
