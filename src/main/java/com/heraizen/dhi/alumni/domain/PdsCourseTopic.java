package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Duration;

/**
 * The PdsCourseTopic entity.\n@author Shreekantha
 */
@Document(collection = "pds_course_topic")
public class PdsCourseTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("duration")
    private Duration duration;

    @DBRef
    @Field("pdsCourse")
    @JsonIgnoreProperties("pdsCourseTopics")
    private PdsCourse pdsCourse;

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

    public PdsCourseTopic name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PdsCourseTopic description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDuration() {
        return duration;
    }

    public PdsCourseTopic duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public PdsCourse getPdsCourse() {
        return pdsCourse;
    }

    public PdsCourseTopic pdsCourse(PdsCourse pdsCourse) {
        this.pdsCourse = pdsCourse;
        return this;
    }

    public void setPdsCourse(PdsCourse pdsCourse) {
        this.pdsCourse = pdsCourse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PdsCourseTopic)) {
            return false;
        }
        return id != null && id.equals(((PdsCourseTopic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PdsCourseTopic{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", duration='" + getDuration() + "'" +
            "}";
    }
}
