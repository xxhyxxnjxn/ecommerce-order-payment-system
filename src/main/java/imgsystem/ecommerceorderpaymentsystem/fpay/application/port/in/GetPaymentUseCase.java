package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;

import java.util.Optional;

public interface GetPaymentUseCase {
    Optional<PaymentLedger> getPaymentStatusAndPaymentId(PaymentStatus paymentStatus,String paymentId);
}
