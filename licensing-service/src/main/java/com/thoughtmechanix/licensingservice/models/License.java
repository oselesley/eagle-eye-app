package com.thoughtmechanix.licensingservice.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "licenses")
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private String id;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;

    @Column(name="comment")
    private String comment;

    public License withId (String id) {
        this.id = id;
        return this;
    }

    public License withOrganizationId(String organization) {
        this.organizationId = organization;
        return this;
    }

    public License withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public License withLicenseType (String licenseType) {
        this.licenseType = licenseType;
        return this;
    }

    public License withlicenseMax (Integer licenseMax) {
        this.licenseMax = licenseMax;
        return this;
    }

    public License withlicenseAllocated (Integer licenseAllocated) {
        this.licenseAllocated = licenseAllocated;
        return this;
    }

    public License withComment (String comment) {
        this.comment = comment;
        return this;
    }
}
