package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.CardPayment;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;

import java.util.Optional;

public interface PaymentRepository {
    PaymentLedger save(PaymentLedger paymentLedger);
    Optional<PaymentLedger> findByPaymentStatusAndPaymentId(PaymentStatus paymentStatus, String paymentId);
}
