package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.heraizen.dhi.alumni.domain.enumeration.ReqOrClaimStatus;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.AlumniReqOrClaim} entity.
 */
@ApiModel(description = "The AlumniRequest entity.\n@author Shreekantha")
public class AlumniReqOrClaimDTO implements Serializable {

    private String id;

    private String requestFor;

    private String reason;

    private ReqOrClaimStatus status;

    private Instant requestedDate;


    private String requestById;

    private String requestByFirstName;

    private String assigneeId;

    private String assigneeFirstName;

    private String subjectId;

    private String subjectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestFor() {
        return requestFor;
    }

    public void setRequestFor(String requestFor) {
        this.requestFor = requestFor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReqOrClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ReqOrClaimStatus status) {
        this.status = status;
    }

    public Instant getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Instant requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getRequestById() {
        return requestById;
    }

    public void setRequestById(String userId) {
        this.requestById = userId;
    }

    public String getRequestByFirstName() {
        return requestByFirstName;
    }

    public void setRequestByFirstName(String userFirstName) {
        this.requestByFirstName = userFirstName;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String userId) {
        this.assigneeId = userId;
    }

    public String getAssigneeFirstName() {
        return assigneeFirstName;
    }

    public void setAssigneeFirstName(String userFirstName) {
        this.assigneeFirstName = userFirstName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String reqOrClaimSubjectId) {
        this.subjectId = reqOrClaimSubjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String reqOrClaimSubjectName) {
        this.subjectName = reqOrClaimSubjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlumniReqOrClaimDTO alumniReqOrClaimDTO = (AlumniReqOrClaimDTO) o;
        if (alumniReqOrClaimDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alumniReqOrClaimDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlumniReqOrClaimDTO{" +
            "id=" + getId() +
            ", requestFor='" + getRequestFor() + "'" +
            ", reason='" + getReason() + "'" +
            ", status='" + getStatus() + "'" +
            ", requestedDate='" + getRequestedDate() + "'" +
            ", requestBy=" + getRequestById() +
            ", requestBy='" + getRequestByFirstName() + "'" +
            ", assignee=" + getAssigneeId() +
            ", assignee='" + getAssigneeFirstName() + "'" +
            ", subject=" + getSubjectId() +
            ", subject='" + getSubjectName() + "'" +
            "}";
    }
}
