package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class PurchaseOrderTest {

    @Test
    public void toEntity_IsNotNull_ConvertDtoToEntity(){
        List<PurchaseOrderItem> purchaseOrderItems = List.of(
                new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, "FREE", 1,  4500)
        );
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(
                new Orderer("정현진", "010-5699-9064"),
                purchaseOrderItems
        );

        Order order = new Order();
        order.setName(newPurchaseOrder.getOrderer().getName());
        order.setPhoneNumber(newPurchaseOrder.getOrderer().getPhoneNumber());
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

        Assertions.assertEquals(order.getOrderItems().size(),1);

    }
}
