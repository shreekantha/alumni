package com.heraizen.dhi.alumni.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The ApiredRole entity.\n@author Shreekantha
 */
@Document(collection = "aspired_role")
public class AspiredRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @DBRef
    @Field("alumniMeetReq")
    private Set<AlumniMeetReq> alumniMeetReqs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AspiredRole name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AspiredRole description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AlumniMeetReq> getAlumniMeetReqs() {
        return alumniMeetReqs;
    }

    public AspiredRole alumniMeetReqs(Set<AlumniMeetReq> alumniMeetReqs) {
        this.alumniMeetReqs = alumniMeetReqs;
        return this;
    }

    public AspiredRole addAlumniMeetReq(AlumniMeetReq alumniMeetReq) {
        this.alumniMeetReqs.add(alumniMeetReq);
        alumniMeetReq.setAspiredRole(this);
        return this;
    }

    public AspiredRole removeAlumniMeetReq(AlumniMeetReq alumniMeetReq) {
        this.alumniMeetReqs.remove(alumniMeetReq);
        alumniMeetReq.setAspiredRole(null);
        return this;
    }

    public void setAlumniMeetReqs(Set<AlumniMeetReq> alumniMeetReqs) {
        this.alumniMeetReqs = alumniMeetReqs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AspiredRole)) {
            return false;
        }
        return id != null && id.equals(((AspiredRole) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AspiredRole{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
