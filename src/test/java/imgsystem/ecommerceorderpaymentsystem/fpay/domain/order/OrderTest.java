package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrderItem;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.Orderer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PurchaseOrder entity 객체
 * define : 주문 도메인의 aggregate
 * description : 주문 업무의 모든 업무 규칙 기능(비지니스 로직) 제공하는 기능
 */
public class OrderTest {

    PurchaseOrder newPurchaseOrder;

    @BeforeEach
    public void init() {
        newPurchaseOrder = new PurchaseOrder(
                new Orderer("정현진", "010-5699-9064"),
                List.of(
                        new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, "FREE", 1,  4500)
                )
        );
    }
    /**
     * 신규 상품 주문(Purchase Order) 관련 단위  테스트
     * - 상품 주문은 최소 1개 이상 주문해야한다.
     * [Test Case #1] 1개 일 때, return true;
     * [Test Case #2] N개 일 때, return true;
     * [Exception] 0개 일 떄, 오류 처리
     */
    @Test
    public void haveAtLeastOneItem_False_ListSizeBiggerThanOne() throws Exception {
        Order order = newPurchaseOrder.toEntity(); // 이거에 대한 메서드 작성 요망

        Assertions.assertTrue(order.hasAtLeastOneItem(order.getOrderItems()));
    }

    @Test
    public void verifyHaveAtLeastOneItem_Exception_ListSizeBiggerThanOne() throws Exception {
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(
                new Orderer("정현진", "010-5699-9064"),
                Collections.emptyList()
        );

        Assertions.assertThrows(Exception.class, () -> newPurchaseOrder.toEntity());
    }

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위  테스트
     * - product_id는 중복될 수 없다.
     * - product_id를 전체 조회해야되니까 orderItem에 작성하지 않고 order에 작성
     * [Test Case #1] 중복 productId count와 주문 상품 아이템 리스트 사이즈가 같으면(중복없다는 것) return true;
     * [Test Case #2] 중복 productId count와 주문 상품 아이템 리스트 사이즈가 같지 않으면 (중복있다는 것) return false;
     * [Exception] null 일 때, 오류처리
     *
     * test case 1번을 먼저 작성하고 다른 메서드에 test case 2번을 작성한 뒤에
     * 코드 정리를 통해서 하나의 메서드로 합치던가 하면 된다.
     */

    @Test
    public void hasDuplicatedProductId_False_existProduceIdInOderItem() throws Exception {
        Order order = newPurchaseOrder.toEntity();

        long distinctProductIdCount = order.getOrderItems().stream().map(OrderItem::getProductId).distinct().count();
        assertThat(distinctProductIdCount).isEqualTo(1);

        assertThat(distinctProductIdCount == order.getOrderItems().size()).isTrue();
    }

    ///여기까지 작성하고 신규 주문에 관련된 로직 작성 시작

    /**
     * 주문 결제 관련 단위 테스트
     * - 결제는 주문 완료 상태 일때만 가능하다.
     * [Test Case #1] orderStatus가 ORDER_COMPLETED이면 return true;
     * [Exception] orderStatus가 그 외 상태이면 exception
     */

    @Test
    public void isOrderCompletedStatus_True_checkOrderStatus() throws Exception {
        Order order = newPurchaseOrder.toEntity();

        boolean isOrderCompletedStatus = OrderStatus.ORDER_COMPLETED.equals(order.getOrderState());
        Assertions.assertTrue(isOrderCompletedStatus);
    }

    /**
     * 주문 결제 관련 단위 테스트
     * - 결제 승인 시 order 엔티티에 payment_id를 업데이트 한다.
     */
    @Test
    public void updatePaymentId_void_setPaymentIdValue() throws Exception {
        Order order = newPurchaseOrder.toEntity();
        String paymentId = "test_20250201";
        order.setPaymentId(paymentId);

        Assertions.assertEquals("test_20250201", order.getPaymentId());
    }

    /**
     * 주문 결제 관련 단위 테스트
     * - 결제 승인 시 order 엔티티에 orderstatus를 PAYMENT_COMPLETED로 업데이트 한다.
     */
    @Test
    public void updateOrderStatusToPaymentCompleted_void_setOrderStatus() throws Exception {
        Order order = newPurchaseOrder.toEntity();
        order.setOrderState(OrderStatus.PAYMENT_COMPLETED);
        order.getOrderItems().stream().forEach(orderItem -> orderItem.setOrderState(OrderStatus.PAYMENT_COMPLETED));

        Assertions.assertEquals(OrderStatus.PAYMENT_COMPLETED,order.getOrderItems().get(0).getOrderState());
    }

    /**
     * 주문 결제 관련 단위 테스트
     * - '구매 결정'의 주문 상태 일 때는 결제 취소를 할 수 없다.
     * [Test Case #1] orderStatus가 구매결정이 아니면 return true;
     * [Test Case #2] orderStatus가 구매결정이면 return false;
     * [Exception] null일 때 exception
     */
    @Test
    public void isNotOrderStatusPurchaseDecision_true_OrderStatusIsNotPurchaseDecision() throws Exception {
        Order order = newPurchaseOrder.toEntity();
        order.setOrderState(OrderStatus.PAYMENT_COMPLETED);

        Assertions.assertTrue(order.isNotOrderStatusPurchaseDecision());
    }

    @Test
    public void countOrderStatus() throws Exception {
        Order order = newPurchaseOrder.toEntity();
        order.setOrderState(OrderStatus.PAYMENT_COMPLETED);

        int count = (int) order.getOrderItems().stream().filter(orderItem -> orderItem.getOrderState().equals(OrderStatus.ORDER_CANCELED)).count();
        if(count < order.getOrderItems().size()) {
            System.out.println(OrderStatus.ORDER_PARTIAL_CANCELED);
        }else {
            System.out.println(OrderStatus.ORDER_CANCELED);

        }
    }
}
