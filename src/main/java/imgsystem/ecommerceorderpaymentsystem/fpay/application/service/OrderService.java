package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewOrderUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.GetOrderUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.OrderRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.NewPurchaseOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateNewOrderUseCase, GetOrderUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public NewPurchaseOrder makeNewOrder(PurchaseOrder purchaseOrder) {
        //request -> entity 변환
        try {
            return NewPurchaseOrder.from(orderRepository.save(purchaseOrder.toEntity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderIdAndPaymentId(UUID orderId, String paymentId) {
        return null;
    }
}
