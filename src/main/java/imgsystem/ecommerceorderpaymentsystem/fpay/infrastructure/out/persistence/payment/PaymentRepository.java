package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.PaymentRepository {

    private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Override
    public PaymentLedger save(PaymentLedger paymentLedger) {
        return jpaPaymentLedgerRepository.save(paymentLedger);
    }

    @Override
    public Optional<PaymentLedger> findByPaymentStatusAndPaymentId(PaymentStatus paymentStatus, String paymentId) {
        return jpaPaymentLedgerRepository.findByPaymentStatusAndPaymentId(paymentStatus, paymentId);
    }
}
