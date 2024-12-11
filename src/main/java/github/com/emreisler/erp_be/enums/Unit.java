package github.com.emreisler.erp_be.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Unit {
    PCS,
    BOX,
    LITERS;

    @JsonCreator
    public static Unit fromValue(String value) {
        for (Unit unit : Unit.values()) {
            if (unit.name().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid value for Unit: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}