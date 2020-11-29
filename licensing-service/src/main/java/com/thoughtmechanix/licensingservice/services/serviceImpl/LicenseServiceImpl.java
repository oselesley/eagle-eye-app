package com.thoughtmechanix.licensingservice.services.serviceImpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.thoughtmechanix.licensingservice.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licensingservice.clients.OrganizationFeignClient;
import com.thoughtmechanix.licensingservice.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licensingservice.config.ServiceConfig;
import com.thoughtmechanix.licensingservice.models.License;
import com.thoughtmechanix.licensingservice.models.Organization;
import com.thoughtmechanix.licensingservice.repositories.LicenseRepository;
import com.thoughtmechanix.licensingservice.services.LicenseService;
import com.thoughtmechanix.licensingservice.utils.UserContextHolder;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseServiceImpl implements LicenseService {
    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;

    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;

    private Logger log = LoggerFactory.logger(LicenseServiceImpl.class);


    private Organization retrieveOrgInfo (String organizationId, String clientType) {
        switch (clientType.toLowerCase()) {
            case "discovery":
                return organizationDiscoveryClient.getOrganization(organizationId);
            case "feign":
                return organizationFeignClient.getOrganization(organizationId);
            default:
                return organizationRestTemplateClient.getOrganization(organizationId);
        }
    }


    @Override
    public License getLicense(String organizationId, String licenseId, String clientType) {
        Organization organization = retrieveOrgInfo(organizationId, clientType);

        License license = licenseRepository.findByOrganizationIdAndId(organizationId, licenseId);

        return license
                .withOrganizationName(organization.getName())
                .withContactEmail(organization.getContactEmail())
                .withContactPhone(organization.getContactPhone())
                .withContactName(organization.getContactName())
                .withComment(config.getProperty());
    }

    @HystrixCommand(
            fallbackMethod = "buildFallBackLicenseList",
            groupKey = "licensesByOrgThreadPool",
            commandKey = "licensesByOrgThreadPool",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
            @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
            },
            threadPoolKey = "licensesByOrgThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    @Override
    public List<License> getLicensesByOrg(String organizationId) {
        log.info("LicenseService.getLicensesByOrg UserContextFilter CorrelationId {}: " +  UserContextHolder.getContext().getCorrelationId());
        randomlyRunLong();

        log.info("retrieving licenses by organization name!");
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private List<License> buildFallBackLicenseList(String organizationId) {
        return Arrays.asList(
            new License()
                .withId("0000000-00-00000")
                .withOrganizationId(organizationId)
                .withProductName("Sorry no Licensing information currently available")
        );
    }

    private void randomlyRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt(4);

        if (randomNum == 3) {
            sleep();
        }

    }

    private void sleep () {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }
}
