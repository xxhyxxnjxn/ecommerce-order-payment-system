package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentApprovalUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentApprovalUseCase {


    @Override
    public String approvePayment(PaymentApproval paymentApproval) {
        //TODO : 저수준 모듈 조합

        return "";
    }
}
