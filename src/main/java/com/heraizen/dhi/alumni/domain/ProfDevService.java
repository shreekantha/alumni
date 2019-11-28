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
 * The ProfDevService entity.\n@author Shreekantha
 */
@Document(collection = "prof_dev_service")
public class ProfDevService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @DBRef
    @Field("pdsCourse")
    private Set<PdsCourse> pdsCourses = new HashSet<>();

    @DBRef
    @Field("pdsSchedule")
    private Set<PdsSchedule> pdsSchedules = new HashSet<>();

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

    public ProfDevService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ProfDevService description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PdsCourse> getPdsCourses() {
        return pdsCourses;
    }

    public ProfDevService pdsCourses(Set<PdsCourse> pdsCourses) {
        this.pdsCourses = pdsCourses;
        return this;
    }

    public ProfDevService addPdsCourse(PdsCourse pdsCourse) {
        this.pdsCourses.add(pdsCourse);
        pdsCourse.setProfDevService(this);
        return this;
    }

    public ProfDevService removePdsCourse(PdsCourse pdsCourse) {
        this.pdsCourses.remove(pdsCourse);
        pdsCourse.setProfDevService(null);
        return this;
    }

    public void setPdsCourses(Set<PdsCourse> pdsCourses) {
        this.pdsCourses = pdsCourses;
    }

    public Set<PdsSchedule> getPdsSchedules() {
        return pdsSchedules;
    }

    public ProfDevService pdsSchedules(Set<PdsSchedule> pdsSchedules) {
        this.pdsSchedules = pdsSchedules;
        return this;
    }

    public ProfDevService addPdsSchedule(PdsSchedule pdsSchedule) {
        this.pdsSchedules.add(pdsSchedule);
        pdsSchedule.setProfDevService(this);
        return this;
    }

    public ProfDevService removePdsSchedule(PdsSchedule pdsSchedule) {
        this.pdsSchedules.remove(pdsSchedule);
        pdsSchedule.setProfDevService(null);
        return this;
    }

    public void setPdsSchedules(Set<PdsSchedule> pdsSchedules) {
        this.pdsSchedules = pdsSchedules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfDevService)) {
            return false;
        }
        return id != null && id.equals(((ProfDevService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProfDevService{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
