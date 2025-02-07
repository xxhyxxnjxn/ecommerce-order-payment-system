package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.kafka;

public interface Producer<T> {
    void send(String topic, T message);
}
