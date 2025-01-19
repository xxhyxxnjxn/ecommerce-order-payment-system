package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orderer {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;
}
