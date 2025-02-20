package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;

import java.util.List;

public interface SettlementRepository {
    void save(PaymentSettlements paymentSettlements);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
