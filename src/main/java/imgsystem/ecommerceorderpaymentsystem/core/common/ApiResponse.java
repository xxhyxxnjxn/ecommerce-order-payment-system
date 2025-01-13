package imgsystem.ecommerceorderpaymentsystem.core.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private String code;
    private T body;
    LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse(String code, T body) {
        this.code = code;
        this.body = body;
    }
}
