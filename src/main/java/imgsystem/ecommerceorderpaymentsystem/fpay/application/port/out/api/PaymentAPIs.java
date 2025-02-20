package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlement;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement.PaymentSettlement;

import java.io.IOException;
import java.util.List;

public interface PaymentAPIs {
    ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException;
    ResponsePaymentCancel cancelPayment(String PaymentKey, PaymentCancel paymentCancel) throws IOException;
    List<ResponsePaymentSettlement> requestSettlement(PaymentSettlement paymentSettlement) throws IOException;
    boolean isSuccessRequest(String status);
}
