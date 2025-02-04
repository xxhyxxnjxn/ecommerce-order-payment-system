package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ResponsePaymentCommon {
    private String mId;
    private String paymentKey;
    private String orderId;
    private String orderName;
    private Integer taxExemptionAmount;
    private String status;
    private String requestedAt;
    private String type;
    private Integer totalAmount;
    private Integer balanceAmount;
}
