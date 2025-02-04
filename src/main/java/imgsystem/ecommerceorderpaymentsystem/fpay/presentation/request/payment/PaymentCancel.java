package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PaymentCancel {
    private final String cancelReason;
    private final int cancelAmount;
}
