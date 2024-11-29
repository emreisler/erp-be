package github.com.emreisler.erp_be.exception;


import github.com.emreisler.erp_be.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static github.com.emreisler.erp_be.error.ErrorCodes.UNKNOWN_ERROR;

@ControllerAdvice
public class ErpExceptionHandler {

    @ExceptionHandler(ErpRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleErpRuntimeException(ErpRuntimeException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResponse.of(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(UNKNOWN_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(UNKNOWN_ERROR, ex.getMessage()));
    }

}
