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
 * The MeetReqTopic entity.\n@author Shreekantha
 */
@Document(collection = "meet_req_topic")
public class MeetReqTopic implements Serializable {

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

    public MeetReqTopic name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MeetReqTopic description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AlumniMeetReq> getAlumniMeetReqs() {
        return alumniMeetReqs;
    }

    public MeetReqTopic alumniMeetReqs(Set<AlumniMeetReq> alumniMeetReqs) {
        this.alumniMeetReqs = alumniMeetReqs;
        return this;
    }

    public MeetReqTopic addAlumniMeetReq(AlumniMeetReq alumniMeetReq) {
        this.alumniMeetReqs.add(alumniMeetReq);
        alumniMeetReq.setTopic(this);
        return this;
    }

    public MeetReqTopic removeAlumniMeetReq(AlumniMeetReq alumniMeetReq) {
        this.alumniMeetReqs.remove(alumniMeetReq);
        alumniMeetReq.setTopic(null);
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
        if (!(o instanceof MeetReqTopic)) {
            return false;
        }
        return id != null && id.equals(((MeetReqTopic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MeetReqTopic{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
