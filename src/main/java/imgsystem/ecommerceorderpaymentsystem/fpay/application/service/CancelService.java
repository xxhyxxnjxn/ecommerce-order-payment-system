package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.GetOrderUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentCancelUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.CancelOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 3. 주문 취소
 * - "구매 확정"된 상품은 주문 취소할 수 없다.
 * - 결제 취소 진행은 상품이 재입고된 이후에 진행된다.
 * - 결제 취소는 부분 취소 및 전체 취소가 가능하다.
 * - 취소 가능한 금액보다 큰 취소 금액은 취소가 불가능하다.
 */
@Service
@RequiredArgsConstructor
public class CancelService implements PaymentCancelUseCase {
    /**
     * 1. purchase_order 테이블에서 paymentId,orderId가 같은 것을 가져온다. && orderItems도 같이 가져옴
     *    purchase_order orderstatus를 확인한다. -> isNotOrderStatusPurchaseDecision -> true이면 다음 로직 실행
     *
     * 2. payment_transaction 테이블에서 paymentStatus가 Done 이고 paymentId가 같은 것을 가져온다.
     *    payment_transaction totalAmount 값이 CancelOrder 객체에 들어있는 값보다 같거나 크다 -> true 이면 다음 로직 실행
     *
     * 3. toss payment에 cancel api를 요청한다
     *    cancel객체 status가 done이면 다음 로직을 실행한다.
     *
     * 4. paymentTransaction status, amount 값 변경 -> 이거 아니고 새로 취소 내역 insert
     *
     * 5. 이 밑에 두개는 한묶음
     *      orderItems cancelOrder에 들어있는 itemidxes 값에 맞는 데이터 값 변경
     *      purchase_order status, price 값 변경
     */
    private final GetOrderUseCase getOrderUseCase;

    @Override
    public boolean cancelPayment(CancelOrder cancelOrder) {
        Order wantedCancelOrder = getOrderUseCase.getOrderIdAndPaymentId(cancelOrder.getOrderId(), cancelOrder.getPaymentKey());
        System.out.println("wantedcancelOrder value : "+ wantedCancelOrder.getOrderId());
        return true;
    }
}
