package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import jakarta.persistence.*;

@Embeddable

public class OrderId {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name="item_idx",nullable = false)
    private int itemIdx;
}
