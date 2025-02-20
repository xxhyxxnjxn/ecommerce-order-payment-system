package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.settlement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PaymentSettlement {
    private String startDate;
    private String endDate;
    private String dateType;
    private int page;
    private int size;
}
