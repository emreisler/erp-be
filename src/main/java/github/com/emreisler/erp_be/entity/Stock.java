package github.com.emreisler.erp_be.entity;

import github.com.emreisler.erp_be.enums.Currency;
import github.com.emreisler.erp_be.enums.Unit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Stock {

    public Stock(UUID uuid, String name, float quantity, float unitPrice, Currency currency,Unit unit, String code) {
        this.uuid = uuid;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.currency = currency;
        this.unit = unit;
        this.code = code;
    }

    public Stock() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private float quantity;

    @NotNull
    private float unitPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    private Unit unit;

    @NotNull
    @Column(unique = true)
    private String code;


}
