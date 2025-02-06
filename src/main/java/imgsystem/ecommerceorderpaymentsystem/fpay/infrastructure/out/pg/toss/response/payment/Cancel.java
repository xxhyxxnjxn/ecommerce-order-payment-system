package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cancel {
    private String transactionKey;
    private String cancelReason;
    private int taxExemptionAmount;
    private String canceledAt;
    private int transferDiscountAmount;
    private int easyPayDiscountAmount;
    private String receiptKey;
    private int cancelAmount;
    private int taxFreeAmount;
    private int refundableAmount;
    private String cancelStatus;
    private String cancelRequestId;

}
