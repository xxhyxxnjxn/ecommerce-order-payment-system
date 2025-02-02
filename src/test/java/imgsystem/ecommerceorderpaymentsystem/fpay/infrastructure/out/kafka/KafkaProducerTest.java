package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.kafka;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KafkaProducerTest {

    @Autowired
    KafkaProducer<String> kafkaProducer;

    @Test
    public void send(){
        kafkaProducer.send("kafkaTest", "hyeonjinTest");
    }

}