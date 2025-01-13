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

    @NotBlank
    private int itemIdx;

    @NotBlank
    private UUID productId;

    @NotBlank
    private String productName;

    @NotBlank
    private int producePrice;

    @NotBlank
    private String produceSize;

    @NotBlank
    private int quantity;

    @NotBlank
    private int amount;
}
