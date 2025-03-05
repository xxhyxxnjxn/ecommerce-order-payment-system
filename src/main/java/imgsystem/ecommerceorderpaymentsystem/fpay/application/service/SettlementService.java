package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.CreateNewPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.GetPaymentSettlementUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.SendSettlementsInfoUseCase;
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
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RPaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.Settlements;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService implements CreateNewPaymentSettlementUseCase, GetPaymentSettlementUseCase, SendSettlementsInfoUseCase
{
    private final static String SETTLEMENT_TOPIC = "settlements";
    private final Producer<PaymentSettlements> producer;
    private final Producer<RPaymentSettlements> settlementsProducer;
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
    public boolean getPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException {
        List<ResponsePaymentSettlement> response = mockPaymentAPI.requestSettlement(paymentSettlement);
        // response 받은거 toEntity로 바꾼다음 builKInsert에 넣기
        List<PaymentSettlements> settlementsHistories = response.stream()
                .map(ResponsePaymentSettlement::toEntity)
                .toList();
        settlementRepository.bulkInsert(settlementsHistories);
        return true;
    }

    @Override
    public boolean send(PaymentSettlement paymentSettlement) throws IOException {
        List<ResponsePaymentSettlement> response = mockPaymentAPI.requestSettlement(paymentSettlement);
        List<PaymentSettlements> settlementsHistories = response.stream()
                .map(ResponsePaymentSettlement::toEntity)
                .toList();
        RPaymentSettlements record = RPaymentSettlements.newBuilder()
                .setSettlements(settlementsHistories.stream().map(data -> Settlements.newBuilder()
                        .setId(data.getId())
                        .setPaymentKey(data.getPaymentKey())
                        .setTotalAmount(data.getTotalAmount())
                        .setPayOutAmount(data.getPayOutAmount())
                        .setCanceledAmount(data.getCanceledAmount())
                        .setMethod(data.getMethod().toString())
                        .setSoldDate(data.getSoldDate().toString())
                        .setPaidOutDate(data.getPaidOutDate().toString())
                        .build()
        ).toList()).build();
        settlementsProducer.send(SETTLEMENT_TOPIC, record);
        return true;
    }
}
