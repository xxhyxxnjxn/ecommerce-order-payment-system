package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    @Column(name="item_idx",nullable = false)
    private int itemIdx;

    @Column(name = "product_id",nullable = false)
    private UUID productId;

    @Column(name="product_name",nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int producePrice;

    @Column(name = "productSize", nullable = false)
    private String produceSize;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "order_state", nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderState;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
