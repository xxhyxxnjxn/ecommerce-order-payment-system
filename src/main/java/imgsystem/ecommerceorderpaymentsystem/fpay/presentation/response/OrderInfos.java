package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfos {

    /**
     * Not null : null 만 비허용
     * Not Empty : null, "" 비허용
     * Not Blank : null, "", " " 모두 비허용
     */

    @NotNull(message = "Not null")
    @Valid
    private Orderer orderer;

    @Size(min = 1)
    @Valid
    private List<OrderInfo> orderInfos;

}
