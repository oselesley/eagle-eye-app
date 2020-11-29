package com.thoughtmechanix.licensingservice.services.serviceImpl;

import com.thoughtmechanix.licensingservice.services.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {
    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    public List<String> getEurekaServices() {
        List<String> eurekaServices = new ArrayList<>();

        discoveryClient.getServices().forEach(serviceName -> discoveryClient.getInstances(serviceName)
        .forEach(serviceInstance -> eurekaServices.add(String.format("%s : %s", serviceName, serviceInstance.getUri()))));

        return eurekaServices;
    }
}
