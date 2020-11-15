package com.thoughtmechanix.licensingservice.repositories;

import com.thoughtmechanix.licensingservice.models.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByOrganizationId (String organizationId);

    License findByOrganizationIdAndId (String organizationId, String id);
}
