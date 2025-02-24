package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.Cancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.fee.Fee;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentSettlement{
    private String mId;
    private String paymentKey;
    private String transactionKey;
    private String orderId;
    private String currency;
    private String method;
    private List<Fee> fees;
    private String approvedAt;
    private String soldDate;
    private String paidOutDate;
    private Card card;
    private Cancel cancel;
    private Integer amount;
    private Integer interestFee; // 할부 수수료 금액
    private Integer fee; //fees 객체에 있는 fee 합친 값
    private Integer supplyAmount; //결제 수수료의 공급가액
    private Integer vat; //결제 수수료 부가세
    private Integer payOutAmount; //지급 금액입니다. 결제 금액 amount에서 수수료인 fee를 제외한 금액입니다.
    // 만약 넘어오는 json에 없는 값이 있으면 어떻게 처리 ??

    private PaymentStatus converterToEntityAttribute() {
        /**
         * - READY: 아직 매입 요청이 안 된 상태입니다.
         *
         * - REQUESTED: 매입이 요청된 상태입니다.
         *
         * - COMPLETED: 요청된 매입이 완료된 상태입니다.
         *
         * - CANCEL_REQUESTED: 매입 취소가 요청된 상태입니다.
         *
         * - CANCELED: 요청된 매입 취소가 완료된 상태입니다.
         */
        switch (card.getAcquireStatus()) {
            case "READY":
            case "REQUESTED":
                return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
            case "COMPLETED":
                return PaymentStatus.valueOf("SETTLEMENTS_COMPLETED");
            case "CANCEL_REQUESTED":
            case "CANCELED":
                return PaymentStatus.valueOf("SETTLEMENTS_CANCELED");
            default:
                return PaymentStatus.valueOf("SETTLEMENTS_REQUESTED");
        }
    }

    public PaymentSettlements toEntity() {
        return PaymentSettlements.builder()
                .paymentKey(paymentKey)
                .method(PaymentMethod.findByMethodName(method))
                .paymentStatus(this.converterToEntityAttribute())
                .totalAmount(amount)
                .payOutAmount(payOutAmount)
                .canceledAmount(cancel == null ? 0 : cancel.getCancelAmount())
                .soldDate(Date.valueOf(soldDate))
                .paidOutDate(Date.valueOf(paidOutDate))
                .build();
    }
}
