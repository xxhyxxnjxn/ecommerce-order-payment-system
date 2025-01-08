package imgsystem.ecommerceorderpaymentsystem.order.controller;

import imgsystem.ecommerceorderpaymentsystem.order.domain.PurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @PostMapping("/new")
    public String newOrder(@RequestBody @Valid PurchaseOrder purchaseOrder){
        return purchaseOrder.toString();
    }
}