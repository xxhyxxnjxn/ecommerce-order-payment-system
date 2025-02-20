package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.Cancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.fee.Fee;
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
    private Integer interestFee;
    private Integer fee;
    private Integer supplyAmount;
    private Integer vat;
    private Integer payOutAmount;
    // 만약 넘어오는 json에 없는 값이 있으면 어떻게 처리 ??
}
