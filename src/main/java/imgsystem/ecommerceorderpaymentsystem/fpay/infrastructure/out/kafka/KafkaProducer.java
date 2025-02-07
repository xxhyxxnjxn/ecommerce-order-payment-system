package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.kafka.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer<T> implements Producer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void send(String topic, T message){
        log.info("sending payloa={} to topic={}", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
