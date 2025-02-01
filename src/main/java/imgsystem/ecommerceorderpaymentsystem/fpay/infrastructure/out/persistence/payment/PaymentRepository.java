package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.PaymentRepository {

    private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Override
    public PaymentLedger save(PaymentLedger paymentLedger) {
        return jpaPaymentLedgerRepository.save(paymentLedger);
    }
}
