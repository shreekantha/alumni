package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The PdsCourse entity.\n@author Shreekantha
 */
@Document(collection = "pds_course")
public class PdsCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("course_name")
    private String courseName;

    @Field("description")
    private String description;

    @DBRef
    @Field("pdsCourseTopic")
    private Set<PdsCourseTopic> pdsCourseTopics = new HashSet<>();

    @DBRef
    @Field("profDevService")
    @JsonIgnoreProperties("pdsCourses")
    private ProfDevService profDevService;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public PdsCourse courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public PdsCourse description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PdsCourseTopic> getPdsCourseTopics() {
        return pdsCourseTopics;
    }

    public PdsCourse pdsCourseTopics(Set<PdsCourseTopic> pdsCourseTopics) {
        this.pdsCourseTopics = pdsCourseTopics;
        return this;
    }

    public PdsCourse addPdsCourseTopic(PdsCourseTopic pdsCourseTopic) {
        this.pdsCourseTopics.add(pdsCourseTopic);
        pdsCourseTopic.setPdsCourse(this);
        return this;
    }

    public PdsCourse removePdsCourseTopic(PdsCourseTopic pdsCourseTopic) {
        this.pdsCourseTopics.remove(pdsCourseTopic);
        pdsCourseTopic.setPdsCourse(null);
        return this;
    }

    public void setPdsCourseTopics(Set<PdsCourseTopic> pdsCourseTopics) {
        this.pdsCourseTopics = pdsCourseTopics;
    }

    public ProfDevService getProfDevService() {
        return profDevService;
    }

    public PdsCourse profDevService(ProfDevService profDevService) {
        this.profDevService = profDevService;
        return this;
    }

    public void setProfDevService(ProfDevService profDevService) {
        this.profDevService = profDevService;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PdsCourse)) {
            return false;
        }
        return id != null && id.equals(((PdsCourse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PdsCourse{" +
            "id=" + getId() +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
