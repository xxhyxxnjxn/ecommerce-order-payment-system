package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.PurchaseOrder;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response.OrderInfos;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("/new")
    public OrderInfos newOrder(@RequestBody @Valid OrderInfos orderInfos){
        return orderInfos;
    }

    @GetMapping
    public String test() throws Exception {
        return "test";
    }
}
