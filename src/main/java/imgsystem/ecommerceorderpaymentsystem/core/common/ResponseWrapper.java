package imgsystem.ecommerceorderpaymentsystem.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Spring MVC에서 HTTP 응답 본문을 변환하거나 조작할 수 있도록 제공하는 인터페이스입니다.
 * 이 인터페이스를 구현하면 모든 컨트롤러 응답에 대해 특정 로직을 실행할 수 있습니다.
 * ResponseBodyAdvice는 @RestController 또는 @ResponseBody가 적용된 컨트롤러 메서드에만 적용됩니다.
 * @RestControllerAdvice를 사용하여 특정 패키지나 클래스에만 적용할 수 있습니다.
 */
@RestControllerAdvice
@Slf4j
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    /**
     * ResponseBodyAdvice가 특정 응답에 대해 적용될지 여부를 결정합니다.
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        /**
         * returnType: 현재 컨트롤러 메서드의 반환 타입입니다. 특정 타입에만 적용하고 싶을 때 사용할 수 있습니다.
         * converterType: 응답을 변환하는 데 사용되는 HttpMessageConverter의 타입입니다. 특정 변환기에만 적용하려면 사용할 수 있습니다.
         */
        // 특정 반환 타입에만 적용
        // return returnType.getParameterType().equals(MyResponse.class);
        return true;
    }

    /**
     * beforeBodyWrite 메서드는 컨트롤러 메서드의 반환값을 변환하거나 조작할 때 사용됩니다.
     * 이 메서드는 HttpMessageConverter가 응답 본문을 변환하기 직전에 호출됩니다.
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        /**
         * body: 컨트롤러 메서드가 반환한 실제 객체입니다. 이 값을 수정하거나 새 객체로 교체할 수 있습니다.
         * returnType: 컨트롤러 메서드의 반환 타입입니다.
         * selectedContentType: 요청된 미디어 타입입니다 (예: application/json, application/xml 등).
         * selectedConverterType: 응답을 변환하는 데 사용될 HttpMessageConverter의 타입입니다.
         * request: 현재 HTTP 요청입니다.
         * response: 현재 HTTP 응답입니다.
         */

        if(body instanceof ErrorResponse) {
            return new ApiResponse<>("ERROR", body);
        }

        return new ApiResponse<>("SUCCESS", body);
    }
}
