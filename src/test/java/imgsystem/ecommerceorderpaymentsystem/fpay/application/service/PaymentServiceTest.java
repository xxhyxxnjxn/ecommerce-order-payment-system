package imgsystem.ecommerceorderpaymentsystem.fpay.application.service;

import imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository.TransactionTypeRepository;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment.PaymentMethod;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.persistence.payment.CardPaymentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    TransactionTypeRepository cardPaymentRepository;

    @InjectMocks
    PaymentService paymentService;

    private final HashMap<String, TransactionTypeRepository> transactionTypeRepositoryHashMap = new HashMap<>();

    @BeforeEach
    public void init() {
        transactionTypeRepositoryHashMap.put("card", cardPaymentRepository);
    }

    @Test
    public void findTransactionTypeRepository_CardPaymentRepository_findPaymentMethodByMethodName(){
        String method = "카드";
        PaymentMethod paymentMethod = PaymentMethod.findByMethodName(method);

        Assertions.assertEquals("card",paymentMethod.toString().toLowerCase());

        Assertions.assertEquals(cardPaymentRepository, transactionTypeRepositoryHashMap.get(paymentMethod.toString().toLowerCase()));
    }

}