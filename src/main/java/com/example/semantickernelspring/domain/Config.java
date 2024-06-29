package com.example.semantickernelspring.domain;

import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class Config implements RestClientCustomizer {


    @Override
    public void customize(RestClient.Builder restClientBuilder) {
        restClientBuilder.requestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
