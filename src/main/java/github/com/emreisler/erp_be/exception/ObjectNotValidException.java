package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotValidException extends ErpRuntimeException {
    public ObjectNotValidException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public ObjectNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message);
    }

}
