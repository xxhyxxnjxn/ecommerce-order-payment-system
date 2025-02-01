package imgsystem.ecommerceorderpaymentsystem.fpay.domain.payment;

import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproval;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @EntityListeners : Auditing을 적용하고 엔티티의 변화를 감지하여 엔티티와 매핑된 테이블을 조작하는 어노테이션
 * AuditingEntityListener.class : Spring data JPA에서 제공하는 이벤트 리스너로 엔티티의 영속성 및 수정 이벤트를 감지하는 역할
 * @MappedSuperclass에서 createdAt, updatedAt과 같은 날짜 필드를 관리하고 싶다면, 반드시 @EntityListeners(AuditingEntityListener.class)를 추가해야 함.
 */

/**
 * @MappedSuperclass의 역할
 * 공통 필드 정의
 * 여러 엔티티에서 반복되는 필드(id, createdAt, updatedAt 등)를 부모 클래스에 모아서 관리할 수 있음.
 * 테이블 매핑 X (독립적인 테이블을 생성하지 않음)
 * 부모 클래스 자체는 테이블로 매핑되지 않음.
 * 대신, 자식 클래스의 테이블에 부모 클래스의 필드가 포함됨.
 * 코드 중복 방지
 * 같은 필드를 여러 엔티티에 반복해서 선언하는 대신, 한 곳에서만 정의하고 사용 가능.
 */
@Data
//@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class TransactionType {
    /**
     * 카드 결제인 response는 카드 도메인에 따로 담고 나중에 계좌이체인 response는 계좌이체 도메인에 따로 담는 확장성을 고려한 코드
     * 그 결제 수단에 국한된 response 데이터를 제외한 공통적인 response를 따로 빼서 파라메터로 받는다.
     */
    /**
     * responsePaymentApproval 객체는 Toentity를 사용했는데 cardPayment는 from 을 쓴 이유
     * toEntity()는 일반적으로 DTO → 엔티티 변환 시 많이 사용함
     * from()은 특정 타입의 객체를 생성할 때 사용함 꼭 dto로 변환을 하지 않아도 사용가능
     * 이유는 TransactionType이 추상 클래스이고, CardPayment가 이를 상속받는 구조이기 때문입니다.
     * responsePaymentApproval에 cardPayment를 entity로 변환하는 코드를 쓰면 되긴하지만 그렇게되면 굳이
     * transactionType의 추상클래스를 구현한 역할이 무의미해지기 때문에 cardPayment에 from으로 코드를 작성하는 것이
     * 가독성에 좋아보여 선택함
     */
    public static TransactionType convertToTransactionType(ResponsePaymentApproval responsePaymentApproval) {
        return switch (responsePaymentApproval.getMethod()) {
            case "카드" -> CardPayment.from(responsePaymentApproval);
            default ->
                throw new RuntimeException("Unsupported TransactionType method ::: " + responsePaymentApproval.getMethod());
        };
    }
}
