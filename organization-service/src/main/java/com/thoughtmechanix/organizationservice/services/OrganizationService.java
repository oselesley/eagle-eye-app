package com.thoughtmechanix.organizationservice.services;

import com.thoughtmechanix.organizationservice.models.Organization;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {
    void createOrganization(Organization organization);

    Organization getOrganization (String organizationName);

    Organization getOrganizationById (String organizationId);

    void removeOrganization(String organizationId);
}
