package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Duration;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.PdsCourseTopic} entity.
 */
@ApiModel(description = "The PdsCourseTopic entity.\n@author Shreekantha")
public class PdsCourseTopicDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private Duration duration;


    private String pdsCourseId;

    private String pdsCourseCourseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getPdsCourseId() {
        return pdsCourseId;
    }

    public void setPdsCourseId(String pdsCourseId) {
        this.pdsCourseId = pdsCourseId;
    }

    public String getPdsCourseCourseName() {
        return pdsCourseCourseName;
    }

    public void setPdsCourseCourseName(String pdsCourseCourseName) {
        this.pdsCourseCourseName = pdsCourseCourseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PdsCourseTopicDTO pdsCourseTopicDTO = (PdsCourseTopicDTO) o;
        if (pdsCourseTopicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pdsCourseTopicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PdsCourseTopicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", duration='" + getDuration() + "'" +
            ", pdsCourse=" + getPdsCourseId() +
            ", pdsCourse='" + getPdsCourseCourseName() + "'" +
            "}";
    }
}
