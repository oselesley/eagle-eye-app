package com.thoughtmechanix.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayserverApplication {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("licensing-service", p -> p
                        .path("/licensing/**")
                        .filters(f -> f.rewritePath("/licensing/(?<path>.*)", "/${path}"))
//                        .uri("http://licensingservice:8080"))
                        .uri("lb://licensingservice"))

                .route("organization-service", p -> p
                        .path("/organization/**")
                        .filters(f -> f.rewritePath("/organization/(?<path>.*)", "/${path}"))
//                        .uri("http://organizationservice:8085"))
                        .uri("lb://organizationservice"))
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

}
