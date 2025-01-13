package imgsystem.ecommerceorderpaymentsystem.core.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List<StackTraceElement> stackTraces;

    public ErrorResponse(HttpStatus status, String message, List<StackTraceElement> stackTraces) {
        this.status = status;
        this.message = message;
        this.stackTraces = stackTraces;
    }
}
