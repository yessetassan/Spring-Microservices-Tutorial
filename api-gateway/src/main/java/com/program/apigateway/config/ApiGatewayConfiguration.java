package com.program.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route( r ->
                        r.path("/currency-exchange/**")
                                .uri("lb://currency-exchange"))
                .route( r ->
                        r.path("/product").filters(f ->
                                        f.rewritePath("product","products"))
                                .uri("https://fakestoreapi.com"))
                .build();
    }
}
