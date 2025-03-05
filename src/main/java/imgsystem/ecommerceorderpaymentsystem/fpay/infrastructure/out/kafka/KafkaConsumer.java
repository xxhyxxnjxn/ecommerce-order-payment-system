package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.SettlementRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final static String SETTLEMENT_TOPIC = "settlements"
    private final SettlementRepository settlementRepository;

      @KafkaListener(topics = "kafkaTest", groupId = "group_1")
      public void listener(Object data) {
          System.out.println("## kafkaConsumer Test ##"+data);
      }

    @KafkaListener(topics = "settlement", groupId = "group_1")
    public void settlementListener(@Payload PaymentSettlements paymentSettlements) {
        System.out.println("## kafkaConsumer settlement ##"+paymentSettlements.getPaymentKey());
        settlementRepository.save(paymentSettlements);
      }

    @KafkaListener(topics = SETTLEMENT_TOPIC)
    public void settlementListener(ConsumerRecord<String, RPaymentSettlements> record) {
        RPaymentSettlements payload = record.value();
        List<Settlements> record = payload.getSettlements();
        List<PaymentSettlements> rows = records.stream().map(record -> PaymentSettlements.builder()
                .paymentKey(record.getPaymentKey())
                .method(PaymentMethod.valueOf(record.getMethod()))
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
                .totalAmount(record.getTotalAmount())
                .payOutAmount(record.getPayOutAmount())
                .canceledAmount(record.getCanceledAmount())
                .soldDate(Date.valueOf(record.getSoldDate()))
                .paidOutDate(Date.valueOf(record.getPaidOutDate()))
        ).toList();
        settlementRepository.bulkInsert(rows);
    }
}
