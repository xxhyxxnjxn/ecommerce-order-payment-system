package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;

public interface OrderRepository {
    public Order save(Order order);
}
