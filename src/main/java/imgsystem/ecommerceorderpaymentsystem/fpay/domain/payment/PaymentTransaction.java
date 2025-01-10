package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name="payment_transaction")
@Getter
@Setter
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="payment_id", nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private String method;

    @Column(name = "total_amount",nullable = false)
    private int totalAmount;

    @Column(name = "balance_amount", nullable = false)
    private int balanceAmount;

    @Column(name = "canceled_amount", nullable = false)
    private int canceledAmount;

    @Column(name = "payment_status", nullable = false)
    @Convert(converter = PaymentStatus.class)
    private PaymentStatus paymentStatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
