package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<Order, UUID> {
    Order findByOrderIdAndPaymentId(UUID orderId, String paymentId);
}
