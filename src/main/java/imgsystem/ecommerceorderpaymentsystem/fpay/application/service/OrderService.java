package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PurchaseOrderFullFill;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements PurchaseOrderFullFill {
    @Override
    public void order(PurchaseOrder purchaseOrder) {

    }
}
