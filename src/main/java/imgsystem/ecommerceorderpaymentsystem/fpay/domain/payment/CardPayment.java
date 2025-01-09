package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CardPayment {

    @Id
    @Column(name="payment_id", nullable = false)
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
}
