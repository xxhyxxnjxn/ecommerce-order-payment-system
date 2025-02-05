package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getOrderIdAndPaymentId(UUID orderId, String paymentId);
}
