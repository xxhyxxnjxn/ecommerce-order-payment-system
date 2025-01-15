package imgsystem.ecommerceorderpaymentsystem.fpay.infrastructure.out.pg.toss;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TossApiClientConfig {
    private static final String BASE_URL = "https://api.tosspayments.com/v1/";
    private static final String SECRET_KEY = "";

    @Bean
    public OkHttpClient okHttpClient() {
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
}
