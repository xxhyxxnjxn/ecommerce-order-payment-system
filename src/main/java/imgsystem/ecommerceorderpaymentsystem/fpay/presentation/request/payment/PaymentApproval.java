package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PaymentApproval {
    private final String amount;
    private final String orderId;
    private final String paymentKey;
}
