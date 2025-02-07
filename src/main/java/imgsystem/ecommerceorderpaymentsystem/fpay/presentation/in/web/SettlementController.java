package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.NewPaymentSettlementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SettlementController {
    private final NewPaymentSettlementUseCase settlementUseCase;

    @GetMapping("settlement")
    public boolean settlement(){
        return settlementUseCase.sendSettlementInfo();
    }
}
