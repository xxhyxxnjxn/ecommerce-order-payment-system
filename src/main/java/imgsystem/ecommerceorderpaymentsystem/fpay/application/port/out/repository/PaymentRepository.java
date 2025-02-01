package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.CardPayment;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;

public interface PaymentRepository {
    PaymentLedger save(PaymentLedger paymentLedger);
}
