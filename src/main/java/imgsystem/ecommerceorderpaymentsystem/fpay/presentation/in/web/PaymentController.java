package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewOrderUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentApprovalUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.NewPurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentApprovalUseCase paymentApprovalUseCase;

    @GetMapping("/success")
    public String fullFillPayment(@RequestParam(value = "paymentType") String paymentType, @RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "paymentKey") String paymentKey, @RequestParam(value = "amount") String amount
    ) {
        return "success";
    }

    @GetMapping("/fail")
    public String failPayment(@RequestParam(value = "message") String message) {
        return "fail";
    }

    @PostMapping("/confirm")
    public String approvePayment(@RequestBody PaymentApproval paymentApproval) throws IOException {

        return paymentApprovalUseCase.approvePayment(paymentApproval);
    }
}
