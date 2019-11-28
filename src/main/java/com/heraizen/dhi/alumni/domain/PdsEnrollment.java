package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * The PdsEnrollment entity.\n@author Shreekantha
 */
@Document(collection = "pds_enrollment")
public class PdsEnrollment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("remarks")
    private String remarks;

    @DBRef
    @Field("enrolledBy")
    @JsonIgnoreProperties("pdsEnrollments")
    private User enrolledBy;

    @DBRef
    @Field("enrolledTo")
    @JsonIgnoreProperties("pdsEnrollments")
    private PdsSchedule enrolledTo;
    
    private String pdsName;
    
    private String courseName;
    
    private String faculty;
    

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public PdsEnrollment remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getEnrolledBy() {
        return enrolledBy;
    }

    public PdsEnrollment enrolledBy(User user) {
        this.enrolledBy = user;
        return this;
    }

    public void setEnrolledBy(User user) {
        this.enrolledBy = user;
    }

    public PdsSchedule getEnrolledTo() {
        return enrolledTo;
    }

    public PdsEnrollment enrolledTo(PdsSchedule pdsSchedule) {
        this.enrolledTo = pdsSchedule;
        return this;
    }

    public void setEnrolledTo(PdsSchedule pdsSchedule) {
        this.enrolledTo = pdsSchedule;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getPdsName() {
        return pdsName;
    }

    public void setPdsName(String pdsName) {
        this.pdsName = pdsName;
    }

    
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PdsEnrollment)) {
            return false;
        }
        return id != null && id.equals(((PdsEnrollment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PdsEnrollment{" +
            "id=" + getId() +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
