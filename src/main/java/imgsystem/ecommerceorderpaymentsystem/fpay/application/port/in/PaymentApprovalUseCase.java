package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in;

import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;

public interface PaymentApprovalUseCase {
    String approvePayment(PaymentApproval paymentApproval);
}
