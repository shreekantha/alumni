package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Duration;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.PdsSchedule} entity.
 */
@ApiModel(description = "The PdsSchedule entity.\n@author Shreekantha")
public class PdsScheduleDTO implements Serializable {

    private String id;

    @NotNull
    private String duration;

    @NotNull
    private LocalDate date;

    @NotNull
    private String time;

    @NotNull
    private String venue;

    private String remarks;


    private Set<UserDTO> users = new HashSet<>();

    private String profDevServiceId;

    private String profDevServiceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public String getProfDevServiceId() {
        return profDevServiceId;
    }

    public void setProfDevServiceId(String profDevServiceId) {
        this.profDevServiceId = profDevServiceId;
    }

    public String getProfDevServiceName() {
        return profDevServiceName;
    }

    public void setProfDevServiceName(String profDevServiceName) {
        this.profDevServiceName = profDevServiceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PdsScheduleDTO pdsScheduleDTO = (PdsScheduleDTO) o;
        if (pdsScheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pdsScheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PdsScheduleDTO{" +
            "id=" + getId() +
            ", duration='" + getDuration() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", venue='" + getVenue() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", profDevService=" + getProfDevServiceId() +
            ", profDevService='" + getProfDevServiceName() + "'" +
            "}";
    }
}
