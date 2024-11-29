package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class StockNotFoundException extends ErpRuntimeException {
    public StockNotFoundException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Stock Not Found");
    }
}
