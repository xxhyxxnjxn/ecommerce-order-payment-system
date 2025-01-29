package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="order_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name="item_idx",nullable = false)
    private int itemIdx;

    @Column(name = "product_id",nullable = false)
    private UUID productId;

    @Column(name="product_name",nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int producePrice;

    @Column(name = "product_size", nullable = false)
    private String produceSize;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "order_state", nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderState;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public int calculateAmount() {
        int totalPrice = producePrice * quantity;
        this.amount = totalPrice;
        return totalPrice;
    }
}
