package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka.config;

import com.fasterxml.jackson.databind.JsonSerializable;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="spring.kafka")
@Getter // 프로퍼티 사용하려면 무조건 setter와 getter가 있어야함
@Setter
public class KafkaProducerConfig {
    private String[] bootstrapServers;

    @Bean
     public ProducerFactory<String, Object> producerFactory() {
      Map<String, Object> config = new HashMap<>();
//      config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19094");
      config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", bootstrapServers));
      config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,  StringSerializer.class);
      config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
      config.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8085");

     //config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
     // 여기에 StringSerializer 를 넣게 되면 send를 보낼 때 value값을 스트링으로만 보낼 수 있다. 객체를 보내게 되면 객체 -> String으로 변환? 시켜줘야함

      return new DefaultKafkaProducerFactory<>(config);
     }

     @Bean
     public KafkaTemplate<String, Object> kafkaTemplate() {
      return new KafkaTemplate<>(producerFactory());
     }
}
