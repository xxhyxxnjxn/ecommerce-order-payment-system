package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentApproval extends ResponsePaymentCommon {
    private String lastTransactionKey;
    private String approvedAt;
    private Card card;

    public PaymentLedger convertToPaymentLedgerEntity(){
        return PaymentLedger.builder()
                .paymentId(this.getPaymentKey())
                .paymentMethod(PaymentMethod.findByMethodName(this.getMethod()))
                .totalAmount(this.getTotalAmount())
                .balanceAmount(this.getBalanceAmount())
                .canceledAmount(0)
                .paymentStatus(PaymentStatus.valueOf(this.getStatus()))
                .payOutAmount(0)
                .build();
    }
}
