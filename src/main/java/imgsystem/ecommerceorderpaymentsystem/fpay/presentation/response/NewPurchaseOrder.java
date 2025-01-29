package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.Orderer;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.UUID;

@Getter
public class NewPurchaseOrder {
    private final UUID orderId;

    private final Orderer orderer;

    private final OrderStatus orderState;

    private final String paymentId;

    private final int totalPrice;

    private final List<NewPurchaseOrderItem> newPurchaseOrderItems;

    public NewPurchaseOrder(UUID orderId, String name, String phoneNumber, OrderStatus orderState, String paymentId, int totalPrice, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderer = new Orderer(name, phoneNumber);
        this.orderState = orderState;
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.newPurchaseOrderItems = NewPurchaseOrderItem.from(orderItems);
    }

    public static NewPurchaseOrder from(Order order) {
        return new NewPurchaseOrder(order.getOrderId(), order.getName(), order.getPhoneNumber(), order.getOrderState(), order.getPaymentId(), order.getTotalPrice(), order.getOrderItems());
    }

}
