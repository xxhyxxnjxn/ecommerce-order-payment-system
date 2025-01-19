package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TossPaymentAPIs {

    @POST("payments/confirm")
    Call<ResponsePaymentApproval> approvePayment(@Body PaymentApproval paymentApproval);
}
