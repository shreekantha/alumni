package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.ReqOrClaimSubject} entity.
 */
@ApiModel(description = "The ReqOrClaimSubject entity.\n@author Shreekantha")
public class ReqOrClaimSubjectDTO implements Serializable {

    private String id;

    private String name;

    private String decsription;


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

    public String getDecsription() {
        return decsription;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO = (ReqOrClaimSubjectDTO) o;
        if (reqOrClaimSubjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reqOrClaimSubjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReqOrClaimSubjectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", decsription='" + getDecsription() + "'" +
            "}";
    }
}
