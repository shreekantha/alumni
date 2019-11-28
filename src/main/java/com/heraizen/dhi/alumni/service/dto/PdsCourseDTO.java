package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.PdsCourse} entity.
 */
@ApiModel(description = "The PdsCourse entity.\n@author Shreekantha")
public class PdsCourseDTO implements Serializable {

    private String id;

    @NotNull
    private String courseName;

    private String description;


    private String profDevServiceId;

    private String profDevServiceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfDevServiceId() {
        return profDevServiceId;
    }

    public void setProfDevServiceId(String profDevServiceId) {
        this.profDevServiceId = profDevServiceId;
    }

    public String getProfDevServiceName() {
        return profDevServiceName;
    }

    public void setProfDevServiceName(String profDevServiceName) {
        this.profDevServiceName = profDevServiceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PdsCourseDTO pdsCourseDTO = (PdsCourseDTO) o;
        if (pdsCourseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pdsCourseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PdsCourseDTO{" +
            "id=" + getId() +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", profDevService=" + getProfDevServiceId() +
            ", profDevService='" + getProfDevServiceName() + "'" +
            "}";
    }
}
