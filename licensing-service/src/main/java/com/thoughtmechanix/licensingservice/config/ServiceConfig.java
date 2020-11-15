package com.thoughtmechanix.licensingservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// The @Value annotation is used to pull configuration data from
// the spring cloud configuration server. Only database configuration is
// auto-injected into the database connection object

@Component
public class ServiceConfig {
    @Value("${example.property}")
    private String property;

    public String getProperty() {
        return property;
    }
}
