package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
}
