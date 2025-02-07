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
}
