package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.payment.Card;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TossPaymentAPITest {

    @Mock
    TossPaymentAPIs server;

    @InjectMocks
    TossPaymentAPI client;

    @Test
    public void approvePayment_2xx_sendPaymentApproval() throws IOException {
        //given
        //request
        PaymentApproval paymentApproval = new PaymentApproval(
            "NORMAL",
                "1000",
                "a4CWyWY5m89PNh7xJwhk1",
                "5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1"
        );

        //response
        ResponsePaymentApproval responsePaymentApproval = ResponsePaymentApproval.builder()
                .mId("tosspayments")
                .lastTransactionKey("9C62B18EEF0DE3EB7F4422EB6D14BC6E")
                .paymentKey("5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1")
                .orderId("a4CWyWY5m89PNh7xJwhk1")
                .orderName("토스 티셔츠 외 2건")
                .taxExemptionAmount(0)
                .status("DONE")
                .requestedAt("2024-02-13T12:17:57+09:00")
                .approvedAt("2024-02-13T12:18:14+09:00")
                .type("NORMAL")
                .method("카드")
                .card(Card.builder()
                        .issuerCode("71")
                        .acquirerCode("71")
                        .number("12345678****000*")
                        .installmentPlanMonths(0)
                        .isInterestFree(false)
                        .interestPayer(null)
                        .approveNo("00000000")
                        .useCardPoint(false)
                        .cardType("신용")
                        .ownerType("개인")
                        .acquireStatus("READY")
                        .receiptUrl("https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213121757MvuS8&ref=PX")
                        .amount(1000).build())
                .totalAmount(1000)
                .balanceAmount(1000)
                .build();

        //given
        when(server.approvePayment(paymentApproval)).thenReturn(Calls.<ResponsePaymentApproval>response(responsePaymentApproval));

        //when
        ResponsePaymentApproval result = client.approvePayment(paymentApproval);

        //then
        verify(server, Mockito.times(1)).approvePayment(paymentApproval);
        Assertions.assertEquals(result, responsePaymentApproval);
    }
}