package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.SettlementRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RPaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.Settlements;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final static String SETTLEMENT_TOPIC = "settlements";
    private final SettlementRepository settlementRepository;

      @KafkaListener(topics = "kafkaTest")
      public void listener(Object data) {
          System.out.println("## kafkaConsumer Test ##"+data);
      }

    @KafkaListener(topics = "settlement")
    public void settlementListener(@Payload PaymentSettlements paymentSettlements) {
        System.out.println("## kafkaConsumer settlement ##"+paymentSettlements.getPaymentKey());
        settlementRepository.save(paymentSettlements);
      }

    @KafkaListener(topics = SETTLEMENT_TOPIC)
    public void settlementListener(ConsumerRecord<String, RPaymentSettlements> consumerRecord) {
        RPaymentSettlements payload = consumerRecord.value();
        List<Settlements> records = payload.getSettlements();
        List<PaymentSettlements> rows = records.stream().map(record -> PaymentSettlements.builder()
                .paymentKey(record.getPaymentKey())
                .method(PaymentMethod.valueOf(record.getMethod()))
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
                .totalAmount(record.getTotalAmount())
                .payOutAmount(record.getPayOutAmount())
                .canceledAmount(record.getCanceledAmount())
                .soldDate(Date.valueOf(record.getSoldDate()))
                .paidOutDate(Date.valueOf(record.getPaidOutDate())).build()
        ).toList();
        settlementRepository.bulkInsert(rows);
    }
}
