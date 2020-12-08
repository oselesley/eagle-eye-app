package com.thoughtmechanix.organizationservice.controllers;

import com.thoughtmechanix.organizationservice.models.Organization;
import com.thoughtmechanix.organizationservice.services.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/organizations")
public class OrganizationServiceController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping({"/", ""})
    public List<Organization> getOrganizations() {
        List<Organization> organizations = organizationService.getOrganizations();
        log.info(organizations + "retriving organizations...");
        return organizations;
    }

    @GetMapping("/{organizationId}")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        log.info("retriving organization with id " + organizationId);
        return organizationService.getOrganizationById(organizationId);
    }

    @PostMapping({"/", ""})
    public void createOrganization(@RequestBody Organization organization) {
        organizationService.createOrganization(organization);
    }

    @DeleteMapping({"/{organizationId}"})
    public void deleteOrganization(@PathVariable("organizationId") String organizationId) {
        organizationService.removeOrganization(organizationId);
    }
}
