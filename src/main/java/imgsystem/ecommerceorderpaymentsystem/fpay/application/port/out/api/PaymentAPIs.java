package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.PaymentApproval;

import java.io.IOException;

public interface PaymentAPIs {
    ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException;
}
