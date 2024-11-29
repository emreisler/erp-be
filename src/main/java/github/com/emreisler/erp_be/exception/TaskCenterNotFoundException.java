package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class TaskCenterNotFoundException extends ErpRuntimeException {
    public TaskCenterNotFoundException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public TaskCenterNotFoundException(String message) {
        super(message);
    }

    public TaskCenterNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Task center not found");
    }
}
