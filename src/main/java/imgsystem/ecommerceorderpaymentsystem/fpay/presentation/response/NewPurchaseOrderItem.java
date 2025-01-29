package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class NewPurchaseOrderItem {

    private final UUID productId;

    private final String productName;

    private final int productPrice;

    private final String productSize;

    private final int quantity;

    private final int amount;

    private final OrderStatus orderState;

    public static List<NewPurchaseOrderItem> from(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> NewPurchaseOrderItem.builder()
                        .productId(orderItem.getProductId())
                        .productName(orderItem.getProductName())
                        .productPrice(orderItem.getProducePrice())
                        .productSize(orderItem.getProduceSize())
                        .quantity(orderItem.getQuantity())
                        .amount(orderItem.getAmount())
                        .orderState(orderItem.getOrderState())
                        .build())
                .toList();
    }
}
