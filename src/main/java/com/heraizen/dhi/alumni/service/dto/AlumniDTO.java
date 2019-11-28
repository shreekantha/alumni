package com.heraizen.dhi.alumni.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.Alumni} entity.
 */
public class AlumniDTO implements Serializable {

    private String id;

    private String usn;

    private String name;

    private String email;

    private String mobile;

    private String degree;

    private String specialization;

    private String jobPosition;

    private String company;

    private String yearOfGraduation;

    private Double experience;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(String yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlumniDTO alumniDTO = (AlumniDTO) o;
        if (alumniDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alumniDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlumniDTO{" +
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
