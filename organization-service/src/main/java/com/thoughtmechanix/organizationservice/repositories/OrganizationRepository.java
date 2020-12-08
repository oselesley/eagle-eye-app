package com.thoughtmechanix.organizationservice.repositories;

import com.thoughtmechanix.organizationservice.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findOrganizationByName (String name);
}
