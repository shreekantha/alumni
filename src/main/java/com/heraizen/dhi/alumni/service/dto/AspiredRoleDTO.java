package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.AspiredRole} entity.
 */
@ApiModel(description = "The ApiredRole entity.\n@author Shreekantha")
public class AspiredRoleDTO implements Serializable {

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

        AspiredRoleDTO aspiredRoleDTO = (AspiredRoleDTO) o;
        if (aspiredRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aspiredRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AspiredRoleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
