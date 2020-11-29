package com.thoughtmechanix.licensingservice.clients;

import com.thoughtmechanix.licensingservice.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Component
public class OrganizationDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("organizationService");

        if (serviceInstances.isEmpty()) return null;
        String serviceUri = String.format("%s/v1/organizations/%s", serviceInstances.get(0).getUri().toString(), organizationId);

        ResponseEntity<Organization> restExchange = restTemplate.exchange(
                serviceUri,
                GET, null,
                Organization.class);

        return restExchange.getBody();
    }
}
