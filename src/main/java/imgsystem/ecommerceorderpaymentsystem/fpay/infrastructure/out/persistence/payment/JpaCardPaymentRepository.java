package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.CardPayment;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;

public interface JpaCardPaymentRepository extends JpaBaseRepository<CardPayment, String> {
}
