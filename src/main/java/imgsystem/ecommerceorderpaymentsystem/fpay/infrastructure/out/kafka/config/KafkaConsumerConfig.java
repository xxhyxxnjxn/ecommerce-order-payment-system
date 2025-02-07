package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka.config;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
 
      @Bean
      public ConsumerFactory<String, PaymentSettlements> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19094");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_1");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

          // ✅ JsonDeserializer에 추가 설정
          JsonDeserializer<PaymentSettlements> deserializer = new JsonDeserializer<>(PaymentSettlements.class);
          deserializer.setRemoveTypeHeaders(false);
          deserializer.addTrustedPackages("*");  // 모든 패키지 허용
          deserializer.setUseTypeMapperForKey(true);

          return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
      }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentSettlements> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentSettlements> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
