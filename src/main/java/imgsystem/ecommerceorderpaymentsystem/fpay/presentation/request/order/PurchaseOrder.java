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

import java.util.ArrayList;
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

    public List<OrderItem> convert2OrderItems(Order o) {
        return purchaseOrderItems.stream()
                .map(items -> convert2OrderItem(items, o))
                .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item, Order o) {
        return OrderItem.builder()
                .order(o)
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .producePrice(item.getProducePrice())
                .quantity(item.getQuantity())
                .produceSize("FREE")
                .orderState(OrderStatus.ORDER_COMPLETED)
                .build();
    }

    public Order toEntity() throws Exception {
        Order o = Order.builder()
                .items(new ArrayList<>())
                .name(this.getOrderer().getName())
                .phoneNumber(this.getOrderer().getPhoneNumber())
                .build();
        o.getOrderItems().addAll(this.convert2OrderItems(o));
        if (!Order.hasAtLeastOneItem(o.getOrderItems())) throw new Exception("Noting Items");
        o.calculateTotalPrice();
        return o;
    }
}
