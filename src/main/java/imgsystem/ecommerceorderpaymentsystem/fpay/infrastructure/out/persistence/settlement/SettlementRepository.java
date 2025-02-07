package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.settlement;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SettlementRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.SettlementRepository {
    private final JpaSettlementRepository jpaSettlementRepository;

    @Override
    public void save(PaymentSettlements paymentSettlements) {
        jpaSettlementRepository.save(paymentSettlements);
    }
}
