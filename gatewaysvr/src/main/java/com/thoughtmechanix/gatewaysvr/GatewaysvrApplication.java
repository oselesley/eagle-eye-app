package com.thoughtmechanix.gatewaysvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@RefreshScope
@Configuration
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class GatewaysvrApplication {
//    @Bean
//    @ConditionalOnBean(ReactiveDiscoveryClient.class)
//    @ConditionalOnProperty(name = "spring.cloud.gateway.discovery.locator.enabled")
//    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
//            ReactiveDiscoveryClient discoveryClient,
//            DiscoveryLocatorProperties properties) {
//        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
//    }
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


    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() { return ServerCodecConfigurer.create(); }
    public static void main(String[] args) { SpringApplication.run(GatewaysvrApplication.class, args); }
}
