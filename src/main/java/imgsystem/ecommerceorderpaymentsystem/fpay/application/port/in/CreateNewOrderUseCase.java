package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.NewPurchaseOrder;

public interface CreateNewOrderUseCase {
    NewPurchaseOrder makeNewOrder(PurchaseOrder purchaseOrder);
}
