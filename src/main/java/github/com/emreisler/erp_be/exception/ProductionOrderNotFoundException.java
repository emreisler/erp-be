package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class ProductionOrderNotFoundException extends ErpRuntimeException {

    public ProductionOrderNotFoundException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public ProductionOrderNotFoundException(String message) {
        super(message);
    }

    public ProductionOrderNotFoundException() {
        super("production order not found");
    }
}
