package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    //주문 완료
    ORDER_COMPLETED("01"),
    //주문 취소
    ORDER_CANCELED("02"),
    //결제 대기
    PAYMENT_READY("03"),
    //결제 완료
    PAYMENT_COMPLETED("04"),
    //결제 취소,
    PAYMENT_FAILED("05"),
    //배송 준비
    SHIPPING_PREPARE("06"),
    //배송 중
    SHIPPING("07"),
    //배송 완료,
    SHIPPING_COMPLETED("08");

    private final String code;

    OrderStatus(String code){
        this.code = code;
    }
}
