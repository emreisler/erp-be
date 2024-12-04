package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends ErpRuntimeException{
    public AuthorizationException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
