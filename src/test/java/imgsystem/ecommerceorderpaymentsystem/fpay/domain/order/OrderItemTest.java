package imgsystem.ecommerceorderpaymentsystem.fpay.domain.order;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderItemTest {

    OrderItem orderItem;

    @BeforeEach
    public void init() {
        orderItem = new OrderItem();
        orderItem.setProducePrice(1000);
        orderItem.setQuantity(1);
    }

    @Test
    public void calculateAmount_ShouldReturn1000_MultiplyPriceAndQuantity() {
       int totalPrice= this.orderItem.getProducePrice() * this.orderItem.getQuantity();
       this.orderItem.setAmount(totalPrice);

        Assertions.assertEquals(totalPrice, 1000);
        Assertions.assertEquals(this.orderItem.getAmount(), 1000);
    }

    @Test
    public void calculateAmount_ShouldReturnZero_MultiplyPriceAndQuantity() {
        this.orderItem.setQuantity(0);

        int totalPrice= this.orderItem.getProducePrice() * this.orderItem.getQuantity();
        this.orderItem.setAmount(totalPrice);

        Assertions.assertEquals(totalPrice, 0);
        Assertions.assertEquals(this.orderItem.getAmount(), 0);
    }
}
