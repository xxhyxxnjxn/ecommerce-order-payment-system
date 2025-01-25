package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderItem {

    private int itemIdx;

    @NotBlank
    private UUID productId;

    @NotBlank
    private String productName;

    private int producePrice;

    private String productSize;

    private int quantity;

    private int amount; // price * quantity
}
