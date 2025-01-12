package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.PurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("/new")
    public String newOrder(@RequestBody @Valid PurchaseOrder purchaseOrder){
        return purchaseOrder.toString();
    }

    @GetMapping
    public String test() throws Exception {
        return "test";
    }
}
