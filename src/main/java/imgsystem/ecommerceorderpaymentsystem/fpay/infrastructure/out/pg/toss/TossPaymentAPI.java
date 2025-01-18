package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api.PaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.PaymentApproval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

/**
 * call 을 날리는 메서드들
 */
@Component
@RequiredArgsConstructor
public class TossPaymentAPI implements PaymentAPIs {
    private TossPaymentAPIs tossPaymentAPIs;

    @Override
    public ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException {
        Response<ResponsePaymentApproval> response = tossPaymentAPIs.approvePayment(paymentApproval).execute();

        if(response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }
}
