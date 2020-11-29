package com.thoughtmechanix.organizationservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "organizations")
public class Organization {
    @Id
    @Column(name = "organization_id", nullable = false, unique = true)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    public Organization withId(String id) {
        this.id = id;
        return this;
    }

    public Organization withName(String name) {
        this.name = name;
        return this;
    }

    public Organization withContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public Organization withContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public Organization withContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }
}
