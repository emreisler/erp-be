package github.com.emreisler.erp_be.exception;


import github.com.emreisler.erp_be.error.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static github.com.emreisler.erp_be.error.ErrorCodes.UNKNOWN_ERROR;

@ControllerAdvice
@Log4j2
public class ErpExceptionHandler {

    @ExceptionHandler(ErpRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleErpRuntimeException(ErpRuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResponse.of(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(UNKNOWN_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(UNKNOWN_ERROR, ex.getMessage()));
    }

}
