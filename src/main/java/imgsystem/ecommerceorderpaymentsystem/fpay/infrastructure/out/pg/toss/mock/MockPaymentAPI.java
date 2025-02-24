package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.mock;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api.PaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.TossPaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlement;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement.PaymentSettlement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * call 을 날리는 메서드들
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MockPaymentAPI implements PaymentAPIs {
    private final MockPaymentAPIs mockPaymentAPIs;

    @Override
    public ResponsePaymentApproval approvePayment(PaymentApproval paymentApproval) throws IOException {
        return null;
    }

    @Override
    public ResponsePaymentCancel cancelPayment(String PaymentKey, PaymentCancel paymentCancel) throws IOException {
        return null;
    }

    @Override
    public List<ResponsePaymentSettlement> requestSettlement(PaymentSettlement paymentSettlement) throws IOException {
        Response<List<ResponsePaymentSettlement>> response = mockPaymentAPIs.settlement().execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public boolean isSuccessRequest(String status) {
        return false;
    }
}
