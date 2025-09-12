package com.ncuindia.edu.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
            .route("product", r -> r.path("/product/**")
                .uri("lb://productservice"))
            .route("supplier", r -> r.path("/supplier/**")
                .uri("lb://supplierservice"))
            .build();
            
    }
}
