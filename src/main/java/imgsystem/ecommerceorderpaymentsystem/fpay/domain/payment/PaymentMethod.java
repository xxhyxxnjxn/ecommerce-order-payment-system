package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum PaymentMethod {
    CARD("카드"),
    ACCOUNT("계좌이체");

    private final String methodName;

    public static PaymentMethod findByMethodName(String methodName) {
        return Arrays.stream(PaymentMethod.values())
                .filter(paymentMethod -> paymentMethod.methodName.equals(methodName))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    PaymentMethod(String name) {
        this.methodName = name;
    }

}
