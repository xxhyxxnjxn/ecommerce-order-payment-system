package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "card_payment")
@Getter
@SuperBuilder
@AllArgsConstructor
public class CardPayment extends TransactionType{

    @Id
    @Column(name="payment_key", nullable = false)
    private String paymentId;

    @Column(name="card_number", nullable = false)
    private String cardNumber;

    @Column(name="approve_no", nullable = false)
    private String approveNo;

    @Column(name="issuer_code", nullable = false)
    private String issuerCode;

    @Column(name="acquirer_code", nullable = false)
    private String acquirerCode;

    @Column(name="acquirer_status", nullable = false)
    private String acquirerStatus;

    @Column(name="acquire_status", nullable = false)
    private String acquireStatus;

    protected CardPayment() {
        // 같은 패키지 또는 자식 클래스에서 사용할 수 있도록 합니다.
    }

    public static CardPayment from(ResponsePaymentApproval responsePaymentApproval) {
        return CardPayment.builder()
                .paymentId(responsePaymentApproval.getPaymentKey())
                .cardNumber(responsePaymentApproval.getCard().getNumber())
                .approveNo(responsePaymentApproval.getCard().getApproveNo())
                .issuerCode(responsePaymentApproval.getCard().getIssuerCode())
                .acquirerCode(responsePaymentApproval.getCard().getAcquirerCode())
                .acquirerStatus(responsePaymentApproval.getCard().getAcquireStatus())
                .acquireStatus(responsePaymentApproval.getCard().getAcquireStatus())
                .build();
    }
}
