package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.NewPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.kafka.Producer;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SettlementService implements NewPaymentSettlementUseCase {
    private final Producer<PaymentSettlements> producer;

    @Override
    public boolean sendSettlementInfo() {
        PaymentSettlements paymentSettlements = PaymentSettlements.builder()
                .paymentKey("kafka-test")
                .method(PaymentMethod.CARD)
                .paymentStatus(PaymentStatus.SETTLEMENTS_COMPLETED)
                .totalAmount(50000)
                .payOutAmount(0)
                .canceledAmount(0)
                .soldDate(Date.valueOf(LocalDate.now()))
                .paidOutDate(Date.valueOf(LocalDate.now()))
                .build();
        producer.send("settlement", paymentSettlements);
        return true;
    }
}
