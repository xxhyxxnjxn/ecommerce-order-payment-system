package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CancelOrderTest {

    /**
     * 주문 취소 관련 단위  테스트
     * - request CancelOrder 객체에 itemIdx 가 존재하지 않으면 전체 취소, itemIdx가 존재하면 부분 취소
     * [Test Case #1] itemIdx가 비어있으면 return true;
     * [Test Case #2] itemIdx가 비어있지 않으면 return false;
     * [Exception] null일 때 오류 처리
     */
    @Test
    public void hasItemIdxes_true_ItemIdxesIsNotEmpty(){
        UUID orderId = UUID.randomUUID();
        int[] itemIndexes = {1};
        CancelOrder cancelOrder = new CancelOrder(
                orderId,
                itemIndexes,
                "TEST",
                "tgen_202502012244327gmR1",
                1000
        );
        Assertions.assertTrue(cancelOrder.getItemIdxes().length > 0);
    }

    @Test
    public void hasItemIdxes_false_ItemIdxesIsEmpty(){
        UUID orderId = UUID.randomUUID();
        int[] itemIndexes = {};
        CancelOrder cancelOrder = new CancelOrder(
                orderId,
                itemIndexes,
                "TEST",
                "tgen_202502012244327gmR1",
                1000
        );
        Assertions.assertFalse(cancelOrder.getItemIdxes().length > 0);
    }

    /**
     * 주문 취소 관련 단위 테스트
     * - 취소 가능한 금액보다 큰 취소 금액은 취소가 불가능하다.
     * [Test Case #1] 취소 요청 금액 < 취소 가능한 금액(잔고) return true;
     * [Test Case #2] 취소 요청 금액 > 취소 가능한 금액(잔고) return false;
     * [Test Case #3] 취소 요청 금액 = 취소 가능한 금액(잔고) return true; -> 전체 취소 ?
     */

}