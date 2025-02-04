package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.CancelOrder;

public interface PaymentCancelUseCase {
    boolean cancelPayment(CancelOrder cancelOrder);
}
