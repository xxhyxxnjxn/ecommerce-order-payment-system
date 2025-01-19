package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.OrderStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class NewPurchaseOrderItem {

    private UUID productId;

    private String productName;

    private int producePrice;

    private String produceSize;

    private int quantity;

    private int amount;

    private OrderStatus orderState;
}
