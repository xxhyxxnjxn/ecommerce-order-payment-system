package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.Orderer;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.UUID;

@Getter
public class NewPurchaseOrder {
    private UUID orderId;

    private Orderer orderer;

    private OrderStatus orderState;

    private String paymentId;

    private int totalPrice;

    private List<NewPurchaseOrderItem> newPurchaseOrderItems;

}
