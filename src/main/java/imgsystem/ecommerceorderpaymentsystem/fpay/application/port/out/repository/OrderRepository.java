package imgsystem.ecommerceorderpaymentsystem.fpay.application.port.out.repository;

import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.Order;

import java.util.Optional;
import java.util.UUID;
/**
 * optional 과 일반 객체 return 값의 차이
 * Optional을 사용한 경우:
 * Optional은 값이 있을 수도 있고 없을 수도 있다는 것을 명시적으로 나타냅니다.
 * Optional로 감싸면 null을 처리할 때 발생할 수 있는 오류를 방지할 수 있습니다.
 * 예를 들어, Optional.empty()와 같은 빈 값을 처리할 수 있으며, isPresent(), ifPresent(), orElse() 등의 메서드를 사용하여 안전하게 값에 접근할 수 있습니다.
 *
 * 일반 객체로 가져오는 경우:
 * 값을 직접 가져오는 방식은 해당 값이 없을 경우 null이 반환됩니다.
 * 따라서 이를 처리하려면 값이 null인지 체크하는 추가 로직이 필요합니다.
 *
 * 핵심 차이점:
 * Optional은 명시적이고 안전한 null 처리를 제공하여 코드의 가독성과 안정성을 높이는 반면,
 * 일반 객체 방식은 null 체크를 명시적으로 해야 하므로 실수로 null을 처리하지 않으면
 * NullPointerException이 발생할 수 있습니다.
 *
 * 어떤 상황에서 사용해야 할까?
 * 값이 없을 수도 있는 상황에서는 Optional을 사용하는 것이 더 안전하고, null 체크를 깜빡할 가능성이 적습니다.
 * 반면, 값이 항상 존재하는 상황이라면 Optional을 사용하지 않아도 되며, 일반 객체를 사용해도 문제가 없습니다.
 */
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByOrderId(UUID orderId);
    Order findByOrderIdAndPaymentId(UUID orderId, String paymentId);
}
