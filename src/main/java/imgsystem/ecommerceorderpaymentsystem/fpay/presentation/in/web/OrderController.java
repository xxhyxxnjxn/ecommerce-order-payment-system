package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewOrderUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.NewPurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateNewOrderUseCase createNewOrderUseCase;

    @PostMapping("/new")
    public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder purchaseOrder){

        return createNewOrderUseCase.makeNewOrder(purchaseOrder);
    }

    @GetMapping
    public String test() throws Exception {
        return "test";
    }
}
