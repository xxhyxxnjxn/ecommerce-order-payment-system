package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class PurchaseOrderTest {

    @Test
    public void toEntity_IsNotNull_(){
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(
                new Orderer("정현진", "010-5699-9064"),
                List.of(
                        new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, "FREE", 1,  4500)
                )
        );

        Order order = newPurchaseOrder.toEntity(); // 이거에 대한 메서드 작성 요망

    }
}
