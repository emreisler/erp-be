package github.com.emreisler.erp_be.domain.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryType {
    SHEET_METAL("Sheet Metal"),
    MACHINING("Machining"),
    COMPOSITE("Composite");

    private final String displayName;

    CategoryType(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static CategoryType fromDisplayName(String displayName) {
        for (CategoryType type : CategoryType.values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown category type: " + displayName);
    }
}