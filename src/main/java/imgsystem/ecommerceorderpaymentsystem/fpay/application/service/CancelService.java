package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentCancelUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentLedger;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.CancelOrder;
import org.springframework.stereotype.Service;

/**
 * 3. 주문 취소
 * - "구매 확정"된 상품은 주문 취소할 수 없다.
 * - 결제 취소 진행은 상품이 재입고된 이후에 진행된다.
 * - 결제 취소는 부분 취소 및 전체 취소가 가능하다.
 */
@Service
public class CancelService implements PaymentCancelUseCase {

    @Override
    public boolean cancelPayment(CancelOrder cancelOrder) {
        return true;
    }
}
