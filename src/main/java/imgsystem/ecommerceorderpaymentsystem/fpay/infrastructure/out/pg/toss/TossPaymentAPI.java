package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api.PaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

/**
 * call 을 날리는 메서드들
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TossPaymentAPI implements PaymentAPIs {
    private final TossPaymentAPIs tossPaymentAPIs;

    @Override
    public ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException {
        Response<ResponsePaymentApproval> response = tossPaymentAPIs.approvePayment(paymentApproval).execute();
        log.info("TossPaymentAPI paymentApproval paymentKey : {}, orderId : {}, amount: {}", paymentApproval.getPaymentKey(), paymentApproval.getOrderId(), paymentApproval.getAmount());
        log.info("TossPaymentAPI approvePayment response : {}", response);

        if(response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public boolean isSuccessRequest(String status) {
        return "DONE".equals(status);
    }


}
