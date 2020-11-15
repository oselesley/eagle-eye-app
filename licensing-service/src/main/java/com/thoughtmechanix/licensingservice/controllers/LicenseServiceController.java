package com.thoughtmechanix.licensingservice.controllers;

import com.thoughtmechanix.licensingservice.models.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @GetMapping("/{licenseId}")
    public License getLicenses (@PathVariable("organizationId") String organizationId,
                                @PathVariable("licenseId") String licenseId) {
        return new License()
                            .withId(licenseId)
                            .withLicenseType("Seat")
                            .withOrganizationId("TestOrg")
                            .withProductName("Teleco");
    }
}
