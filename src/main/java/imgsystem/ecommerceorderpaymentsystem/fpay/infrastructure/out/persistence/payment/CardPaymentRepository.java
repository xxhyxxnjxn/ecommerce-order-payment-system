package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.TransactionTypeRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.CardPayment;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardPaymentRepository implements TransactionTypeRepository {

    private final JpaCardPaymentRepository jpaCardPaymentRepository;

    @Override
    public TransactionType save(TransactionType transactionType) {

        return jpaCardPaymentRepository.save((CardPayment) transactionType);
    }
}
