package com.heraizen.dhi.alumni.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Alumni.
 */
@Document(collection = "alumni")
public class Alumni implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("usn")
    private String usn;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("mobile")
    private String mobile;

    @Field("degree")
    private String degree;

    @Field("specialization")
    private String specialization;

    @Field("job_position")
    private String jobPosition;

    @Field("company")
    private String company;

    @Field("year_of_graduation")
    private String yearOfGraduation;

    @Field("experience")
    private Double experience;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsn() {
        return usn;
    }

    public Alumni usn(String usn) {
        this.usn = usn;
        return this;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public Alumni name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Alumni email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public Alumni mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDegree() {
        return degree;
    }

    public Alumni degree(String degree) {
        this.degree = degree;
        return this;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Alumni specialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public Alumni jobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getCompany() {
        return company;
    }

    public Alumni company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYearOfGraduation() {
        return yearOfGraduation;
    }

    public Alumni yearOfGraduation(String yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
        return this;
    }

    public void setYearOfGraduation(String yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public Double getExperience() {
        return experience;
    }

    public Alumni experience(Double experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Alumni)) {
            return false;
        }
        return id != null && id.equals(((Alumni) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Alumni{" +
            "id=" + getId() +
            ", usn='" + getUsn() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", degree='" + getDegree() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", jobPosition='" + getJobPosition() + "'" +
            ", company='" + getCompany() + "'" +
            ", yearOfGraduation='" + getYearOfGraduation() + "'" +
            ", experience=" + getExperience() +
            "}";
    }
}
