package imgsystem.ecommerceorderpaymentsystem.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    private String name;
    private String phoneNumber;
}
