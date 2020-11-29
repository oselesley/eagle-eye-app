package com.thoughtmechanix.licensingservice.clients;

import com.thoughtmechanix.licensingservice.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.*;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        ResponseEntity<Organization> restExchange = restTemplate
                .exchange("http://organizationservice/v1/organizations/{organizationId}",
                        GET, null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
