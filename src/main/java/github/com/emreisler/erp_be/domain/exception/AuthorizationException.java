package github.com.emreisler.erp_be.domain.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends ErpRuntimeException {

    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), message);
    }
}
