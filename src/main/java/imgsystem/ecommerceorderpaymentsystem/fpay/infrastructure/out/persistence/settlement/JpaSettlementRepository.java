package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.settlement;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;

public interface JpaSettlementRepository extends JpaBaseRepository<PaymentSettlements, Integer> {
}
