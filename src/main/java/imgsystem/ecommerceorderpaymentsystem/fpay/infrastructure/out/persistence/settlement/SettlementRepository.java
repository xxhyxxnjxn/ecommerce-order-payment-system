package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.settlement;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SettlementRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.SettlementRepository {
    private final JpaSettlementRepository jpaSettlementRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(PaymentSettlements paymentSettlements) {
        jpaSettlementRepository.save(paymentSettlements);
    }

    @Override
    public void bulkInsert(List<PaymentSettlements> paymentSettlements) {
        String sqlStatement = "INSERT INTO payment_settlements (payment_id, method, settlements_status, total_amount, pay_out_amount, canceled_amount, sold_date, paid_out_date)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sqlStatement,
                paymentSettlements,
                paymentSettlements.size(),
                (PreparedStatement ps, PaymentSettlements data) -> {
                    ps.setString(1, data.getPaymentKey());
                    ps.setString(2, data.getMethod().getMethodName());
                    ps.setString(3, data.getPaymentStatus().toString());
                    ps.setInt(4, data.getTotalAmount());
                    ps.setInt(5, data.getPayOutAmount());
                    ps.setInt(6, data.getCanceledAmount());
                    ps.setDate(7, data.getSoldDate());
                    ps.setDate(8, data.getPaidOutDate());
                }
        );
    }
}
