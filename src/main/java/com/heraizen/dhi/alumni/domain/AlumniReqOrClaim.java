package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.heraizen.dhi.alumni.domain.enumeration.ReqOrClaimStatus;

/**
 * The AlumniRequest entity.\n@author Shreekantha
 */
@Document(collection = "alumni_req_or_claim")
public class AlumniReqOrClaim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("request_for")
    private String requestFor;

    @Field("reason")
    private String reason;

    @Field("status")
    private ReqOrClaimStatus status;

    @Field("requested_date")
    private Instant requestedDate;

    @DBRef
    @Field("requestBy")
    @JsonIgnoreProperties("alumniReqOrClaims")
    private User requestBy;

    @DBRef
    @Field("assignee")
    @JsonIgnoreProperties("alumniReqOrClaims")
    private User assignee;

    @DBRef
    @Field("subject")
    @JsonIgnoreProperties("alumniReqOrClaims")
    private ReqOrClaimSubject subject;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestFor() {
        return requestFor;
    }

    public AlumniReqOrClaim requestFor(String requestFor) {
        this.requestFor = requestFor;
        return this;
    }

    public void setRequestFor(String requestFor) {
        this.requestFor = requestFor;
    }

    public String getReason() {
        return reason;
    }

    public AlumniReqOrClaim reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReqOrClaimStatus getStatus() {
        return status;
    }

    public AlumniReqOrClaim status(ReqOrClaimStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ReqOrClaimStatus status) {
        this.status = status;
    }

    public Instant getRequestedDate() {
        return requestedDate;
    }

    public AlumniReqOrClaim requestedDate(Instant requestedDate) {
        this.requestedDate = requestedDate;
        return this;
    }

    public void setRequestedDate(Instant requestedDate) {
        this.requestedDate = requestedDate;
    }

    public User getRequestBy() {
        return requestBy;
    }

    public AlumniReqOrClaim requestBy(User user) {
        this.requestBy = user;
        return this;
    }

    public void setRequestBy(User user) {
        this.requestBy = user;
    }

    public User getAssignee() {
        return assignee;
    }

    public AlumniReqOrClaim assignee(User user) {
        this.assignee = user;
        return this;
    }

    public void setAssignee(User user) {
        this.assignee = user;
    }

    public ReqOrClaimSubject getSubject() {
        return subject;
    }

    public AlumniReqOrClaim subject(ReqOrClaimSubject reqOrClaimSubject) {
        this.subject = reqOrClaimSubject;
        return this;
    }

    public void setSubject(ReqOrClaimSubject reqOrClaimSubject) {
        this.subject = reqOrClaimSubject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlumniReqOrClaim)) {
            return false;
        }
        return id != null && id.equals(((AlumniReqOrClaim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlumniReqOrClaim{" +
            "id=" + getId() +
            ", requestFor='" + getRequestFor() + "'" +
            ", reason='" + getReason() + "'" +
            ", status='" + getStatus() + "'" +
            ", requestedDate='" + getRequestedDate() + "'" +
            "}";
    }
}
