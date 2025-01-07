package github.com.emreisler.erp_be.domain.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ErpRuntimeException{
    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), message);
    }
}
