package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;

public interface PurchaseOrderFullFill {
    public void order(PurchaseOrder purchaseOrder);
}
