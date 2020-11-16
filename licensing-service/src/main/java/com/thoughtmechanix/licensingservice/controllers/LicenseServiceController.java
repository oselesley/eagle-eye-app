package com.thoughtmechanix.licensingservice.controllers;

import com.thoughtmechanix.licensingservice.models.License;
import com.thoughtmechanix.licensingservice.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public License getLicense (@PathVariable("organizationId") String organizationId,
                                @PathVariable("licenseId") String licenseId) {
        return new License()
                            .withId(licenseId)
                            .withLicenseType("Seat")
                            .withOrganizationId("TestOrg")
                            .withProductName("Teleco");
    }

    @GetMapping("/")
    public List<License> getLicenses (@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }
}
