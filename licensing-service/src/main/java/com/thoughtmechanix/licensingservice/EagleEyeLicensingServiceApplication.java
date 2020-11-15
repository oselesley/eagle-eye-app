package com.thoughtmechanix.licensingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RefreshScope
//@SpringBootApplication()
public class EagleEyeLicensingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleEyeLicensingServiceApplication.class, args);
    }

}
