package imgsystem.ecommerceorderpaymentsystem.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import imgsystem.ecommerceorderpaymentsystem.fpay.domain.order.PurchaseOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // rest doc 사용할 수 있게하는 설정 파일이 있는데 그 파일을 커스텀할 수도 있고 이렇게 어노테이션으로 사용 가능
@ExtendWith(RestDocumentationExtension.class)
public class OrderController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void newOrder_2XX_CorrectConstraintValue() throws Exception {
        //controller가 있어야만 test 동작 가능
        PurchaseOrder purchaseOrder = new PurchaseOrder("정현진", "010-5699-9064");

        String requestJson = objectMapper.writeValueAsString(purchaseOrder);
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/order/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(document("CorrectRequestMessage",
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("name").description("주문자명")
                                                .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
                                        PayloadDocumentation.fieldWithPath("phoneNumber").description("주문자 휴대전화 번호")
                                                .attributes((key("constraint").value("주문자명을 입력 해주세요.")))
                                )
                        )
                );
    }
}
