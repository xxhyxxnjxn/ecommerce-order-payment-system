package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    /**
     * Not null : null 만 비허용
     * Not Empty : null, "" 비허용
     * Not Blank : null, "", " " 모두 비허용
     */

    @NotNull(message = "Not null")
    @Valid
    private Orderer orderer;

    @Size(min = 1)
    @Valid
    private List<PurchaseOrderItem> purchaseOrderItems;

    public Order toEntity() {
        Order order = new Order();
        order.setName(orderer.getName());
        order.setPhoneNumber(orderer.getPhoneNumber());
        order.setOrderState(OrderStatus.ORDER_COMPLETED);

        List<OrderItem> orderItems = purchaseOrderItems.stream()
                .map(purchaseOrderItem -> OrderItem.builder()
                        .order(order)
                        .itemIdx(purchaseOrderItem.getItemIdx())
                        .productId(purchaseOrderItem.getProductId())
                        .productName(purchaseOrderItem.getProductName())
                        .producePrice(purchaseOrderItem.getProducePrice())
                        .produceSize(purchaseOrderItem.getProductSize())
                        .quantity(purchaseOrderItem.getQuantity())
                        .amount(purchaseOrderItem.getAmount())
                        .orderState(OrderStatus.ORDER_COMPLETED).build()
                ).toList();

        order.setOrderItems(orderItems);
        order.calculateTotalPrice();

        return order;
    }
}
