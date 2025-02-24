package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss.TossPaymentAPIs;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class MockApiClientConfig {
    private static final String BASE_URL = "https://a16d7bf0-802e-422b-82bf-a7aec8715555.mock.pstmn.io/";
    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:";

    @Bean
    public OkHttpClient mockClient() { // 메소드의 명칭에 따라 빈이 등록되기 때문에 메소드 명을 바꿔줘야한다.
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] keyBytes = encoder.encode((SECRET_KEY+":").getBytes(StandardCharsets.UTF_8));
        String authorization = "Basic " + new String(keyBytes);
        log.info("key : {}", authorization);

        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain ->  {
                    Request original = chain.request().newBuilder().addHeader("Authorization", authorization).build();
                    return chain.proceed(original);
                })
                .build();
    }

    @Bean
    public Retrofit mockRetrofit(OkHttpClient mockClient) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // request message에 시간 관련 타입을 설정해주려고
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(mockClient)
                .build();
    }

    @Bean
    public MockPaymentAPIs createMockApiClient(Retrofit mockRetrofit){
        return mockRetrofit.create(MockPaymentAPIs.class);
    }
}
