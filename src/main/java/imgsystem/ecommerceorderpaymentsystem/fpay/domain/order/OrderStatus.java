package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    //주문 완료
    ORDER_COMPLETED("01"),
    //주문 부분 취소
    ORDER_PARTIAL_CANCELED("02"),
    //주문 취소
    ORDER_CANCELED("03"),
    //결제 대기
    PAYMENT_READY("04"),
    //결제 완료
    PAYMENT_COMPLETED("05"),
    //결제 부분 취소
    PAYMENT_PARTIAL_FAILED("06"),
    //결제 취소,
    PAYMENT_FAILED("07"),
    //배송 준비
    SHIPPING_PREPARE("08"),
    //배송 중
    SHIPPING("09"),
    //배송 완료,
    SHIPPING_COMPLETED("10"),
    //구매 결정
    PURCHASE_DECISION("11");

    private final String code;

    OrderStatus(String code){
        this.code = code;
    }
}
