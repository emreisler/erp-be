package github.com.emreisler.erp_be.exception;

import github.com.emreisler.erp_be.error.ErrorCodes;
import org.springframework.http.HttpStatus;

public class ErpRuntimeException extends RuntimeException{
    private final HttpStatus status;
    private final int code;
    private final String message;

    public ErpRuntimeException(HttpStatus status, int code, String message){
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErpRuntimeException(String message){
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ErrorCodes.UNKNOWN_ERROR;
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
