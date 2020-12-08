package com.thoughtmechanix.organizationservice.services;

import com.thoughtmechanix.organizationservice.models.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    void createOrganization(Organization organization);

    Organization getOrganization (String organizationName);

    Organization getOrganizationById (String organizationId);

    List<Organization> getOrganizations();

    void removeOrganization(String organizationId);
}
