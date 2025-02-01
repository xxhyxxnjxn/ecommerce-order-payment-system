package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;

public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, Integer> {
}
