package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ErpRuntimeException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message);
    }
}
