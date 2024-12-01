package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class PartNotFoundException extends ErpRuntimeException {
    public PartNotFoundException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public PartNotFoundException(String message) {
        super(message);
    }

    public PartNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Part not found");
    }
}
