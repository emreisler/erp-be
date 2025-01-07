package github.com.emreisler.erp_be.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {
    USD,
    TL,
    EURO;

    @JsonCreator
    public static Currency fromValue(String value) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equalsIgnoreCase(value)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Invalid value for Currency: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
