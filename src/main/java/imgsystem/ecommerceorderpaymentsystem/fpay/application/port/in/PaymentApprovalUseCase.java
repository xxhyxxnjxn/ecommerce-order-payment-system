package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;

import java.io.IOException;

public interface PaymentApprovalUseCase {
    String approvePayment(PaymentApproval paymentApproval) throws IOException;
}
