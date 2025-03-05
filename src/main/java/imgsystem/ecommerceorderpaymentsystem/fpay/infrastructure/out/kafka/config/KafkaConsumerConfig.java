package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka.config;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="spring.kafka")
@Setter // 프로퍼티 사용하려면 무조건 setter와 getter가 있어야함
@Getter
public class KafkaConsumerConfig {
    private String[] bootstrapServers;
    private String groupId;

    @Bean
    public ConsumerFactory<String, PaymentSettlements> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",",bootstrapServers));
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        config.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8085");
        config.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        //true이면 컨슈머에서 제네링 avro 레코드(.avro 파일)에서 만들어진 avro 클래스를 사용한다. (예 : RPaymentSettlements)
        //false 이면 record 파일을 그대로 가져와서 사용함.

          return new DefaultKafkaConsumerFactory<>(config);
      }

    //json 버전
//      @Bean
//      public ConsumerFactory<String, PaymentSettlements> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19094");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_1");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//          // ✅ JsonDeserializer에 추가 설정
//          JsonDeserializer<PaymentSettlements> deserializer = new JsonDeserializer<>(PaymentSettlements.class);
//          deserializer.setRemoveTypeHeaders(false);
//          deserializer.addTrustedPackages("*");  // 모든 패키지 허용
//          deserializer.setUseTypeMapperForKey(true);
//
//          return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//      }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentSettlements> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentSettlements> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
