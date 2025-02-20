package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement.PaymentSettlement;

import java.io.IOException;

public interface GetPaymentSettlementUseCase {
    void getPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException;
}
