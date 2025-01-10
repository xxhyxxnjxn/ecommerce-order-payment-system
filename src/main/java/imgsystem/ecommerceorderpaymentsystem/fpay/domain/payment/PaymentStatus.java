package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    //완료
    DONE("01"),
    //부분 취소
    PARTIAL_CANCELED("02"),
    //취소
    CANCELED("03");

    private final String code;

    PaymentStatus(String code) {
        this.code = code;
    }
}
