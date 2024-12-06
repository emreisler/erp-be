package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ErpRuntimeException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message);
    }
}
