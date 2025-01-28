package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.JpaBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderRepository implements imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public Order save(Order order) {

    }
}
