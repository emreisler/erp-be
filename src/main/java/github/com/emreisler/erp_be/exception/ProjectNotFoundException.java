package github.com.emreisler.erp_be.exception;

import org.springframework.http.HttpStatus;

public class ProjectNotFoundException extends ErpRuntimeException {
    public ProjectNotFoundException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }

    public ProjectNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Project not found");
    }
}
