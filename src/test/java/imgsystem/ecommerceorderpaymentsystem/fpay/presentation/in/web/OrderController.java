package imgsystem.ecommerceorderpaymentsystem.fpay.presentation.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;

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
//        Order order = new Order("정현진", "010-5699-9064"); // 1. Request DTO 를 생성한다.
//
//        String requestJson = objectMapper.writeValueAsString(order); // 2. Request DTO를 ObjectMapper를 이용해서 Json 형식으로 변환한다.
//        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/order/new") // 3-1. MockMVC를 이용해서 Http 요청을 정의한다.
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isOk()) // 3-2. 요청에 대한 응답 상태를 예상한다.
//                .andDo(document("CorrectRequestMessage", // 3-3. API Documents의 이름과 Req/Resp Message Format을 정의한다.
//                                PayloadDocumentation.requestFields(
//                                        PayloadDocumentation.fieldWithPath("name").description("주문자명")
//                                                .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
//                                        PayloadDocumentation.fieldWithPath("phoneNumber").description("주문자 휴대전화 번호")
//                                                .attributes((key("constraint").value("주문자명을 입력 해주세요.")))
//                                )
//                        )
//                );
    }
}
