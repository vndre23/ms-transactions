package com.nttdata.bootcamp.mstransactions.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AppConfig {

    @Value("${config.base.endpoint.personal.product}")
    private String baseUrlPersonalProduct;

    

    @Value("${config.base.endpoint.customer.personal}")
    private String baseUrlCustomerPersonal;

    @Value("${config.base.endpoint.product}")
    private String baseUrlProduct;

    @Bean
    @LoadBalanced
    @Qualifier("personal-product")
    public WebClient.Builder registerWebClientPersonalProduct() {
        System.out.println("========================================================================");
        System.out.println(baseUrlPersonalProduct);
        return WebClient.builder().baseUrl(baseUrlPersonalProduct);
    }

    @Bean
    @LoadBalanced
    @Qualifier("customer-personal")
    public WebClient.Builder registerWebClientPersonal() {
        System.out.println("========================================================================");
        System.out.println(baseUrlCustomerPersonal);
        return WebClient.builder().baseUrl(baseUrlCustomerPersonal);
    }

    @Bean
    @LoadBalanced
    @Qualifier("product")
    public WebClient.Builder registerWebClientProduct() {
        System.out.println("========================================================================");
        System.out.println(baseUrlProduct);
        return WebClient.builder().baseUrl(baseUrlProduct);
    }
}
