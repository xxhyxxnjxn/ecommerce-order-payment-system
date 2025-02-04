package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Entity
@Table(name="purchase_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    private UUID orderId;

    @Column(nullable = false)
    private String name;

    @Column(name="phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "order_state",nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderState;

    @Column(name="payment_id")
    private String paymentId;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Builder
    public Order(String name, String phoneNumber, List<OrderItem> items) throws Exception {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.orderState = OrderStatus.ORDER_COMPLETED;
        this.orderItems = items;
    }

    public void calculateTotalPrice(){
        this.totalPrice = orderItems.stream().map(OrderItem::calculateAmount).reduce(0, Integer::sum);
    }

    public static boolean hasAtLeastOneItem(List<OrderItem> items) {
        if(!items.isEmpty() || items != null) return true;

        throw new IllegalStateException();
    }

    public boolean hasDuplicatedProductId() {
        long distinctProductIdCount = this.getOrderItems().stream().map(OrderItem::getProductId).distinct().count();
        if(distinctProductIdCount == this.getOrderItems().size()) return true;

        throw new IllegalStateException();
    }

    public boolean isOrderCompletedStatus(){
        if(!OrderStatus.ORDER_COMPLETED.getCode().equals(orderState.getCode()))
            throw new IllegalStateException("order status is not ORDER_COMPLETED");

        return true;
    }

    public void updatePaymentApprove(String paymentKey){
        updateOrderStatus(OrderStatus.PAYMENT_COMPLETED);
        this.paymentId = paymentKey;
    }


    public boolean isNotOrderStatusPurchaseDecision() {

        return !(OrderStatus.PURCHASE_DECISION.equals(this.orderState));
    }

    private void updateOrderStatus(OrderStatus orderState){
        this.orderState = orderState;
        this.orderItems.stream().forEach(orderItem -> orderItem.updateOrderStatus(orderState));
    }
}
