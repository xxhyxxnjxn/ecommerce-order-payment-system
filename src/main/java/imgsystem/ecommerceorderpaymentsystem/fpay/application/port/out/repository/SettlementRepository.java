package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;

public interface SettlementRepository {
    void save(PaymentSettlements paymentSettlements);
}
