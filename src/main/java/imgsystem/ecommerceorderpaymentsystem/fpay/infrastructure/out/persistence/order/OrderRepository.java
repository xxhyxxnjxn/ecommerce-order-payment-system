package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Optional<Order> findByOrderId(UUID orderId) {
        return jpaOrderRepository.findById(orderId);
    }

    @Override
    public Order findByOrderIdAndPaymentId(UUID orderId, String paymentId) {
        return jpaOrderRepository.findByOrderIdAndPaymentId(orderId, paymentId);
    }


}
