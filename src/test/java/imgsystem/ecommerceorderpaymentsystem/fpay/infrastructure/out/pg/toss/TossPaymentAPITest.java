package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentCancel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;
import retrofit2.mock.Calls;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TossPaymentAPITest {

    @Autowired
    TossPaymentAPIs tossPaymentAPIs;

    @Test
    public void cancelPayment_2xx_sendPaymentCancel() throws IOException {
        PaymentCancel paymentCancel = new PaymentCancel(
                "TEST",
                100
        );
        String paymentKey = "tgen_202502012244327gmR1";
        Response<ResponsePaymentCancel> response = tossPaymentAPIs.cancelPayment(paymentKey, paymentCancel).execute();
        System.out.println(response);
        System.out.println(response.body().getCancels().get(0).getCancelStatus());
    }
}