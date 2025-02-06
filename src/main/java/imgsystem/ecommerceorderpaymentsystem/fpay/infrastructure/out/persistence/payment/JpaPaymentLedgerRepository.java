package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;

import java.util.Optional;

public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, Integer> {
    Optional<PaymentLedger> findByPaymentStatusAndPaymentId(PaymentStatus paymentStatus, String paymentId);
}
