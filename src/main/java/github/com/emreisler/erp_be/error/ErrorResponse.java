package github.com.emreisler.erp_be.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Long timestamp;
    private int code;
    private String message;

    public static ErrorResponse of(int code,String message) {
        return new ErrorResponse(code,message);
    }

    public ErrorResponse(int code, String message) {
        this.timestamp = Instant.now().toEpochMilli();
        this.code = code;
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
