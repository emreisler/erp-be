package github.com.emreisler.erp_be.dto;

import github.com.emreisler.erp_be.enums.Unit;

import java.util.UUID;

public class StockDto {

    public StockDto(UUID uuid, String name, float quantity, float unitPrice, Unit unit, String code) {
        this.uuid = uuid;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.code = code;
    }

    public UUID uuid;
    public String name;
    public float quantity;
    public float unitPrice;
    public Unit unit;
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
}
