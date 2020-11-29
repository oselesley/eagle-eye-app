package com.thoughtmechanix.licensingservice.controllers;

import com.netflix.discovery.converters.Auto;
import com.thoughtmechanix.licensingservice.services.DiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.Tool;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolsController {
    @Autowired
    private DiscoveryService discoveryService;

    Logger log = LoggerFactory.getLogger(ToolsController.class);

    @GetMapping("")
    public List<String> getEurekaServices() {
        log.info("retrieving eureka service instances!!");

        return discoveryService.getEurekaServices();
    }
}
