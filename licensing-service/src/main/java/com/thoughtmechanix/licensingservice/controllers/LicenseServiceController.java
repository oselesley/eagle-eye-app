package com.thoughtmechanix.licensingservice.controllers;

import com.thoughtmechanix.licensingservice.models.License;
import com.thoughtmechanix.licensingservice.services.LicenseService;
import com.thoughtmechanix.licensingservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(LicenseServiceController.class);

    @GetMapping("/")
    public List<License> getLicenses (@PathVariable("organizationId") String organizationId) {
        log.info("LicenseServiceController.getLicense: UserContextFilter CorrelationId {}: " + UserContextHolder.getContext().getCorrelationId());

        return licenseService.getLicensesByOrg(organizationId);
    }

    @GetMapping("/{licenseId}")
    public License getLicense (@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId, "");
    }

    @GetMapping("/{licenseId}/{clientType}")
    public License getLicenseWithClient(@PathVariable("organizationId") String organizationId,
                                               @PathVariable("licenseId") String licenseId,
                                               @PathVariable("clientType") String clientType) {

        return licenseService.getLicense(organizationId, licenseId, clientType);
    }
}
