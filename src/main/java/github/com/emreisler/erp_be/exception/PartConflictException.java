package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class PartConflictException extends ErpRuntimeException {
    public PartConflictException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public PartConflictException(String message) {
        super(message);
    }

    public PartConflictException() {
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "Part number already exists");
    }

}
