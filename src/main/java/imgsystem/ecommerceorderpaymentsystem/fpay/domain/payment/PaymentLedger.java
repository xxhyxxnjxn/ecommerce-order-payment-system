package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import jakarta.persistence.*;
import lombok.*;

/**
 * 금융권에서는 거래 원장이라는 말을 많이 함
 * 거래 원장은 어떤 사람이 이마트에서 우유를 샀을 때
 * 그거에 대한 거래내역을 이마트에서 적어놓으면 이 원장들을 모아서 이마트는
 * 카드사에서 정산을 받음
 *
 * 여기에 들어온 값들은 변하지 않음
 * 결제를 했다가 취소를 해서 재결제를 해도 그 히스토리가 남지
 * 값을 변경시키지 않음
 *
 */

@Entity
@Table(name="payment_transaction")
@Getter
@Builder
@AllArgsConstructor
public class PaymentLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="payment_id", nullable = false)
    private String paymentId; //임의로 만들어주는 것이 아닌 pg사에서 전달해주는 key? 같은거

    @Column(name = "method", nullable = false)
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod paymentMethod;

    @Column(name = "total_amount",nullable = false)
    private int totalAmount;

    @Column(name = "balance_amount", nullable = false)
    private int balanceAmount;

    @Column(name = "canceled_amount", nullable = false)
    private int canceledAmount;

    @Column(name = "payment_status", nullable = false)
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus paymentStatus;

    @Column(name = "pay_out_amount")
    private int payOutAmount;

    protected PaymentLedger() {

    }

    /**
     * 주문 취소 관련 단위 테스트
     * - 취소 가능한 금액보다 큰 취소 금액은 취소가 불가능하다.
     * [Test Case #1] 취소 요청 금액 < 취소 가능한 금액(잔고) return true;
     * [Test Case #2] 취소 요청 금액 > 취소 가능한 금액(잔고) return false;
     * [Test Case #3] 취소 요청 금액 = 취소 가능한 금액(잔고) return true; -> 전체 취소 ?
     */
    public boolean isBalanceAmountBiggerThanCancelAmount(int cancelOrderAmount){
        return balanceAmount >= cancelOrderAmount;
    }
}
