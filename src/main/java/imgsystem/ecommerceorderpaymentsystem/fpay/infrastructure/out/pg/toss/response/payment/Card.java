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
public class Card {
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String installmentPlanMonths;
    private String isInterestFree;
    private String interestPayer;
    private String approveNo;
    private String useCardPoint;
    private String cardType;
    private String ownerType;
    private String acquireStatus;
    private String receiptUrl;
    private String amount;
}
