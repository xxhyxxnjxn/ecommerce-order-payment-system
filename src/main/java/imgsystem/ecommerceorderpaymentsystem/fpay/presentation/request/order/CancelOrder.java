package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrder {
    private UUID orderId;
    private int[] itemIdxes;         // itemIdx 정보가 Empty면 전체 취소
    private String cancelReason;    // 취소 사유
    @NotBlank
    private String paymentKey;      // 결제 ID
    private int cancellationAmount; // 취소 금액

    public boolean hasItemIdxes(){
        return this.itemIdxes != null && this.itemIdxes.length > 0;
    }
}
