package github.com.emreisler.erp_be.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachedStockDto {
    private String code;
    private String name;
    private int quantity;

}
