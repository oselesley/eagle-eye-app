package com.thoughtmechanix.licensingservice.services;

import com.thoughtmechanix.licensingservice.models.License;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {
    License getLicense (String organizationId, String licenseId, String clientType);

    List<License> getLicensesByOrg (String organizationId);

    void saveLicense (License license);
}
