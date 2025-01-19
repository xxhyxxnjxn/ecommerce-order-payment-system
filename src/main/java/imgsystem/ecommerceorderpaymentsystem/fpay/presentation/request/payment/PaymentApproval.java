package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment;

import lombok.Getter;

@Getter
public class PaymentApproval {
    private String paymentType;
    private String amount;
    private String orderId;
    private String paymentKey;
}
