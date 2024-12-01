package github.com.emreisler.erp_be.entity;

import github.com.emreisler.erp_be.enums.Unit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Stock {

    public Stock(UUID uuid, String name, float quantity, float unitPrice, Unit unit, String code) {
        this.uuid = uuid;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
    private Unit unit;

    @NotNull
    @Column(unique = true)
    private String code;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
