package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentCancel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TossPaymentAPIs {

    @POST("payments/confirm")
    Call<ResponsePaymentApproval> approvePayment(@Body PaymentApproval paymentApproval);

    @POST("payments/{paymentKey}/cancel")
    Call<ResponsePaymentCancel> cancelPayment(@Path("paymentKey") String paymentKey , @Body PaymentCancel paymentCancel);
}
