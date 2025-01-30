package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @EntityListeners : Auditing을 적용하고 엔티티의 변화를 감지하여 엔티티와 매핑된 테이블을 조작하는 어노테이션
 * AuditingEntityListener.class : Spring data JPA에서 제공하는 이벤트 리스너로 엔티티의 영속성 및 수정 이벤트를 감지하는 역할
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class TransactionType {
    /**
     * 카드 결제인 response는 카드 도메인에 따로 담고 나중에 계좌이체인 response는 계좌이체 도메인에 따로 담는 확장성을 고려한 코드
     * 그 결제 수단에 국한된 response 데이터를 제외한 공통적인 response를 따로 빼서 파라메터로 받는다.
     */
//    public static TransactionType convertToTransactionType(ResponsePaymentCommon commonMessage) {
//        return switch (commonMessage.getMethod()) {
//            case "카드" -> CardPayment.from((ResponsePaymentApproved) commonMessage);
//            default ->
//                throw new RuntimeException("Unsupported TransactionType method ::: " + commonMessage.getMethod());
//        };
//    }
}
