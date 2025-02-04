package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentCancelUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.CancelOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.NewPurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CancelController {

    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping("/cancel")
    public boolean cancel(@RequestBody @Valid CancelOrder cancelOrder){
        //주문 취소
       return paymentCancelUseCase.cancelPayment(cancelOrder);
    }
}
