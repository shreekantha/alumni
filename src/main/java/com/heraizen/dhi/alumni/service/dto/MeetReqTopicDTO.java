package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.MeetReqTopic} entity.
 */
@ApiModel(description = "The MeetReqTopic entity.\n@author Shreekantha")
public class MeetReqTopicDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    private String description;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MeetReqTopicDTO meetReqTopicDTO = (MeetReqTopicDTO) o;
        if (meetReqTopicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), meetReqTopicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MeetReqTopicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
