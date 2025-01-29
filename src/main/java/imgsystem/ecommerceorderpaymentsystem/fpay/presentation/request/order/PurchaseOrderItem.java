package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import jakarta.validation.constraints.Min;
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

    @Min(1)
    private int itemIdx;

    private UUID productId;

    @NotBlank
    private String productName;

    private int producePrice;

    private String productSize;

    @Min(1)
    private int quantity;

    private int amount; // price * quantity
}
