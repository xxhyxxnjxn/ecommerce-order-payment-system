package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.CancelOrder;

import java.io.IOException;

public interface PaymentCancelUseCase {
    boolean cancelPayment(CancelOrder cancelOrder) throws Exception;
}
