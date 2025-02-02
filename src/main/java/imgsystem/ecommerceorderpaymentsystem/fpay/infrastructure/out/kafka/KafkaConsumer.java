package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

      @KafkaListener(topics = "kafkaTest", groupId = "group_1")
      public void listener(Object data) {
          System.out.println("## kafkaConsumer Test ##"+data);
      }
}
