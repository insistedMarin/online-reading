package org.wy.online_reading.core.config;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * @author 89589
 */
@Configuration
@Slf4j
public class EsConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Configure the ObjectMapper to ignore unknown properties globally
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    JacksonJsonpMapper jacksonJsonpMapper(ObjectMapper objectMapper) {
        return new JacksonJsonpMapper(objectMapper);
    }

    @ConditionalOnProperty(value = "spring.elasticsearch.ssl.verification-mode", havingValue = "none")
    @Bean
    RestClient elasticsearchRestClient(RestClientBuilder restClientBuilder,
                                       ObjectProvider<RestClientBuilderCustomizer> builderCustomizers) {
        restClientBuilder.setHttpClientConfigCallback((HttpAsyncClientBuilder clientBuilder) -> {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                log.error("Elasticsearch RestClient config failed！", e);
            }
            assert sc != null;
            clientBuilder.setSSLContext(sc);
            clientBuilder.setSSLHostnameVerifier((hostname, session) -> true);

            builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(clientBuilder));
            return clientBuilder;
        });
        return restClientBuilder.build();
    }

}
