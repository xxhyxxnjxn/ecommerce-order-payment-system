package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.Cancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentCancel extends ResponsePaymentCommon {
    private List<Cancel> cancels;

    public PaymentLedger toEntity(){
        return PaymentLedger.builder()
                .paymentId(this.getPaymentKey())
                .paymentMethod(PaymentMethod.findByMethodName(this.getMethod()))
                .totalAmount(this.getTotalAmount())
                .balanceAmount(this.getBalanceAmount())
                .canceledAmount(this.cancels.stream().map(Cancel::getCancelAmount).reduce(0,Integer::sum))
                .paymentStatus(PaymentStatus.valueOf(this.getStatus()))
                .payOutAmount(0)
                .build();
    }
}
