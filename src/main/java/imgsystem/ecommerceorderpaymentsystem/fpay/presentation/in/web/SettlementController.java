package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.GetPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement.PaymentSettlement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SettlementController {
    private final CreateNewPaymentSettlementUseCase settlementUseCase;
    private final GetPaymentSettlementUseCase getPaymentSettlementUseCase;

    @GetMapping("settlement")
    public boolean settlement(){
        return settlementUseCase.sendSettlementInfo();
    }

    @GetMapping("bulk-settlement")
    public boolean bulkSettlement(PaymentSettlement paymentSettlement) throws IOException {
        return getPaymentSettlementUseCase.getPaymentSettlement(paymentSettlement);
    }

    @GetMapping("kafka-settlement")
    public boolean kafkaBulkSettlement(PaymentSettlement paymentSettlement) throws IOException {
        return getPaymentSettlementUseCase.send(paymentSettlement);
    }
}
