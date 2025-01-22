package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.Orderer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

/**
 * PurchaseOrder entity 객체
 * define : 주문 도메인의 aggregate
 * description : 주문 업무의 모든 업무 규칙 기능(비지니스 로직) 제공하는 기능
 */
public class OrderTest {
    /**
     * 신규 상품 주문(Purchase Order) 관련 단위  테스트
     * - 상품 주문은 최소 1개 이상 주문해야한다.
     * [Test Case #1] 1개 일 때, return true;
     * [Test Case #2] N개 일 때, return true;
     * [Exception] 0개 일 떄, 오류 처리
     */
    @Test
    public void verifyHaveAtLeastOneItem_False_ListSizeBiggerThanOne() throws Exception {
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(
                new Orderer("정현진", "010-5699-9064"),
                List.of(
                        new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, "FREE", 1,  4500)
                )
        );

        Order order = newPurchaseOrder.toEntity(); // 이거에 대한 메서드 작성 요망

        Assertions.assertEquals(order.verifyHaveAtLeastOneItem(order.getOrderItems()), true);
    }
}
