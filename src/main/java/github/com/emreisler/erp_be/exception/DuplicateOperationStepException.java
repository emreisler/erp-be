package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class DuplicateOperationStepException extends ErpRuntimeException{
    public DuplicateOperationStepException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public DuplicateOperationStepException(String message) {
        super(message);
    }
}
