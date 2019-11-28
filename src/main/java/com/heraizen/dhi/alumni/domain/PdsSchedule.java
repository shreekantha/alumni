package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * The PdsSchedule entity.\n@author Shreekantha
 */
@Document(collection = "pds_schedule")
public class PdsSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("duration")
    private Duration duration;

    @NotNull
    @Field("date")
    private LocalDate date;

    @NotNull
    @Field("time")
    private Instant time;

    @NotNull
    @Field("venue")
    private String venue;

    @Field("remarks")
    private String remarks;

    @DBRef
    @Field("pdsEnrollment")
    private Set<PdsEnrollment> pdsEnrollments = new HashSet<>();

    @DBRef
    @Field("users")
    private Set<User> users = new HashSet<>();

    @DBRef
    @Field("profDevService")
    @JsonIgnoreProperties("pdsSchedules")
    private ProfDevService profDevService;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public PdsSchedule duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public PdsSchedule date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Instant getTime() {
        return time;
    }

    public PdsSchedule time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public PdsSchedule venue(String venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRemarks() {
        return remarks;
    }

    public PdsSchedule remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<PdsEnrollment> getPdsEnrollments() {
        return pdsEnrollments;
    }

    public PdsSchedule pdsEnrollments(Set<PdsEnrollment> pdsEnrollments) {
        this.pdsEnrollments = pdsEnrollments;
        return this;
    }

    public PdsSchedule addPdsEnrollment(PdsEnrollment pdsEnrollment) {
        this.pdsEnrollments.add(pdsEnrollment);
        pdsEnrollment.setEnrolledTo(this);
        return this;
    }

    public PdsSchedule removePdsEnrollment(PdsEnrollment pdsEnrollment) {
        this.pdsEnrollments.remove(pdsEnrollment);
        pdsEnrollment.setEnrolledTo(null);
        return this;
    }

    public void setPdsEnrollments(Set<PdsEnrollment> pdsEnrollments) {
        this.pdsEnrollments = pdsEnrollments;
    }

    public Set<User> getUsers() {
        return users;
    }

    public PdsSchedule users(Set<User> users) {
        this.users = users;
        return this;
    }

    public PdsSchedule addUser(User user) {
        this.users.add(user);
        return this;
    }

    public PdsSchedule removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public ProfDevService getProfDevService() {
        return profDevService;
    }

    public PdsSchedule profDevService(ProfDevService profDevService) {
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
        if (!(o instanceof PdsSchedule)) {
            return false;
        }
        return id != null && id.equals(((PdsSchedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PdsSchedule{" +
            "id=" + getId() +
            ", duration='" + getDuration() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", venue='" + getVenue() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
