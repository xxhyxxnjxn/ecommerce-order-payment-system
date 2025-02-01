package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.in.PaymentApprovalUseCase;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.api.PaymentAPIs;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.PaymentRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.TransactionTypeRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.TransactionType;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.order.OrderRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.payment.PaymentApproval;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentApprovalUseCase {
    private final PaymentRepository paymentRepository;
    private final Set<TransactionTypeRepository> transactionTypeRepositories;
    private final HashMap<String, TransactionTypeRepository> transactionTypeRepositoryHashMap = new HashMap<>();
    private final OrderRepository orderRepository;
    private final PaymentAPIs tossPaymentAPI;

    private TransactionTypeRepository transactionTypeRepository;

    @PostConstruct
    public void init(){
        for(TransactionTypeRepository transactionTypeRepository : transactionTypeRepositories) {
            String paymentMethodType = transactionTypeRepository.getClass().getSimpleName().split("PaymentRepository")[0].toLowerCase();
            transactionTypeRepositoryHashMap.put(paymentMethodType, transactionTypeRepository);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public String approvePayment(PaymentApproval paymentApproval) throws IOException {
        //TODO : 저수준 모듈 조합 , 비지니스 로직이 웬만해서는 들어가지 않게 코드 작성
        //1. paymentApproval객체에 있는 orderId로 purchase_order 디비 조회해서 orderstatus 값 가져오기
        Order order = findByOrderId(paymentApproval.getOrderId());

        //2. orderstatus 값 확인해서 completed 체크
        order.isOrderCompletedStatus();

        //3. tosspaymentapi approvePayment 호출
        ResponsePaymentApproval responsePaymentApproval = tossPaymentAPI.approvePayment(paymentApproval);

        //성공이면
        if(tossPaymentAPI.isSuccessRequest(responsePaymentApproval.getStatus())) {
            //4. payment_transaction 테이블에 값 save
            paymentRepository.save(responsePaymentApproval.convertToPaymentLedgerEntity());

            //5. card_transaction 테이블에도 값 save
            findTransactionTypeRepository(responsePaymentApproval.getMethod());
            transactionTypeRepository.save(TransactionType.convertToTransactionType(responsePaymentApproval));

            //6. purchase_order orderstatus 상태값 update
            //7. purchase_order payment_id에 paymentKey 값 update
            order.updatePaymentApprove(responsePaymentApproval.getPaymentKey());

            //8. 모두 완료하면 "success" 값 return
            return "success";
        }

        return "fail";
    }

    private Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(UUID.fromString(orderId)).get();
    }

    private void findTransactionTypeRepository(String method){
        PaymentMethod paymentMethod = PaymentMethod.findByMethodName(method);

        transactionTypeRepository = transactionTypeRepositoryHashMap.get(paymentMethod.toString().toLowerCase());
        if(transactionTypeRepository == null) throw new IllegalStateException("transactionTypeRepository is null");
    }
}
