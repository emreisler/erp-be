package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class StepNumberNotPositiveException extends ErpRuntimeException {


    public StepNumberNotPositiveException(String message) {
        super(message);
    }

    public StepNumberNotPositiveException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Step number not positive");
    }
}
