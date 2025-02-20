package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.GetPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api.PaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.kafka.Producer;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.SettlementRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentStatus;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.settlements.PaymentSettlements;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlement;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement.PaymentSettlement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService implements CreateNewPaymentSettlementUseCase, GetPaymentSettlementUseCase
{
    private final Producer<PaymentSettlements> producer;
    private final PaymentAPIs mockPaymentAPI;
    private final SettlementRepository settlementRepository;

    @Override
    public boolean sendSettlementInfo() {
        PaymentSettlements paymentSettlements = PaymentSettlements.builder()
                .paymentKey("kafka-test")
                .method(PaymentMethod.CARD)
                .paymentStatus(PaymentStatus.SETTLEMENTS_COMPLETED)
                .totalAmount(50000)
                .payOutAmount(0)
                .canceledAmount(0)
                .soldDate(Date.valueOf(LocalDate.now()))
                .paidOutDate(Date.valueOf(LocalDate.now()))
                .build();
        producer.send("settlement", paymentSettlements);
        return true;
    }

    @Override
    public void getPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException {
        List<ResponsePaymentSettlement> response = mockPaymentAPI.requestSettlement(paymentSettlement);
        // response 받은거 toEntity로 바꾼다음 builKInsert에 넣기
        // settlementRepository.bulkInsert();
    }
}
