package com.thoughtmechanix.organizationservice.controllers;

import com.thoughtmechanix.organizationservice.models.Organization;
import com.thoughtmechanix.organizationservice.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/organizations")
public class OrganizationServiceController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/{organizationId}")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.getOrganizationById(organizationId);
    }

    @PostMapping({"/", ""})
    public void createOrganization(@RequestBody Organization organization) {
        organizationService.createOrganization(organization);
    }

    @PostMapping({"/{organizationId}"})
    public void deleteOrganization(@PathVariable("organizationId") String organizationId) {
        organizationService.removeOrganization(organizationId);
    }
}
