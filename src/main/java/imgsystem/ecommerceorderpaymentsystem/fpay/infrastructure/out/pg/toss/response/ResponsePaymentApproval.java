package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentApproval {
    private String mId;
    private String lastTransactionKey;
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String taxExemptionAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private String type;
    private String method;
    private Card card;
    private String totalAmount;
    private String balanceAmount;
}
