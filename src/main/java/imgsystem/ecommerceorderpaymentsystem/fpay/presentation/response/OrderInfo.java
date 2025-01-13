package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

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
public class OrderInfo {

    private int itemIdx;

    private UUID productId;

    @NotBlank
    private String productName;

    private int producePrice;

    @NotBlank
    private String produceSize;

    private int quantity;

    private int amount;
}
