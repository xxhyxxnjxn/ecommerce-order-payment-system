package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentCancel;

import java.io.IOException;

public interface PaymentAPIs {
    ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException;
    ResponsePaymentCancel cancelPayment(String PaymentKey, PaymentCancel paymentCancel) throws IOException;
    boolean isSuccessRequest(String status);
}
