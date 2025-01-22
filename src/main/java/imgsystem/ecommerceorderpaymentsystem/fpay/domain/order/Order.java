package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

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

    public static boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items.isEmpty() || items == null;
    }
}
