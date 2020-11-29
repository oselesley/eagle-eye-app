package com.thoughtmechanix.organizationservice.services.serviceImpl;


import com.thoughtmechanix.organizationservice.models.Organization;
import com.thoughtmechanix.organizationservice.repositories.OrganizationRepository;
import com.thoughtmechanix.organizationservice.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void createOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public Organization getOrganization(String organizationName) {
        return organizationRepository.findOrganizationByName(organizationName);
    }

    @Override
    public Organization getOrganizationById(String organizationId) {
        return organizationRepository.findOrganizationById(organizationId);
    }

    @Override
    public void removeOrganization(String organizationId) {
        Organization organization = getOrganizationById(organizationId);
        organizationRepository.delete(organization);
    }
}
