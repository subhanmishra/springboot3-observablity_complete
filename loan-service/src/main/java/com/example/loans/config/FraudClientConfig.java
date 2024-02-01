package com.example.loans.config;

import com.example.loans.client.FraudDetectionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FraudClientConfig {

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")
                .requestInterceptor((request, body, execution) -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication == null) {
                        return execution.execute(request, body);
                    }

                    if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
                        return execution.execute(request, body);
                    }

                    AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
                    request.getHeaders().setBearerAuth(token.getTokenValue());
                    return execution.execute(request, body);
                })
                .requestFactory(new JdkClientHttpRequestFactory())
                .build();
    }

    @Bean
    FraudDetectionClient fraudDetectionClient(RestClient restClient) {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(FraudDetectionClient.class);

    }

//    @Bean
//    RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder
//                .additionalInterceptors((request, body, execution) -> {
//                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                    if (authentication == null) {
//                        return execution.execute(request, body);
//                    }
//
//                    if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
//                        return execution.execute(request, body);
//                    }
//
//                    AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
//                    request.getHeaders().setBearerAuth(token.getTokenValue());
//                    return execution.execute(request, body);
//                })
//                .requestFactory(() -> new JdkClientHttpRequestFactory())
//                .build();
//    }
}
