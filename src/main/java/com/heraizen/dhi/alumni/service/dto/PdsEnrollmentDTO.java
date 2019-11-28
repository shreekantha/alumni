package com.heraizen.dhi.alumni.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.PdsEnrollment} entity.
 */
@ApiModel(description = "The PdsEnrollment entity.\n@author Shreekantha")
public class PdsEnrollmentDTO implements Serializable {

    private String id;

    private String remarks;

    private String enrolledById;

    private String enrolledByFirstName;

    private String enrolledToId;

    private String pdsName;
    private String faculty;
    
    private PdsScheduleDTO enrolledTo;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getRemarks() {
	return remarks;
    }

    public void setRemarks(String remarks) {
	this.remarks = remarks;
    }

    public String getEnrolledById() {
	return enrolledById;
    }

    public void setEnrolledById(String userId) {
	this.enrolledById = userId;
    }

    public String getEnrolledByFirstName() {
	return enrolledByFirstName;
    }

    public void setEnrolledByFirstName(String userFirstName) {
	this.enrolledByFirstName = userFirstName;
    }

    public String getEnrolledToId() {
	return enrolledToId;
    }

    public void setEnrolledToId(String pdsScheduleId) {
	this.enrolledToId = pdsScheduleId;
    }

    

    
    public String getPdsName() {
        return pdsName;
    }

    public void setPdsName(String pdsName) {
        this.pdsName = pdsName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    

    public PdsScheduleDTO getEnrolledTo() {
        return enrolledTo;
    }

    public void setEnrolledTo(PdsScheduleDTO enrolledTo) {
        this.enrolledTo = enrolledTo;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	PdsEnrollmentDTO pdsEnrollmentDTO = (PdsEnrollmentDTO) o;
	if (pdsEnrollmentDTO.getId() == null || getId() == null) {
	    return false;
	}
	return Objects.equals(getId(), pdsEnrollmentDTO.getId());
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
	return "PdsEnrollmentDTO{" + "id=" + getId() + ", remarks='" + getRemarks() + "'" + ", enrolledBy="
		+ getEnrolledById() + ", enrolledBy='" + getEnrolledByFirstName() + "'" + ", enrolledTo="
		+ getEnrolledToId() + "}";
    }
}
