package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;

public interface OrderRepository {
    Order save(Order order);
}
