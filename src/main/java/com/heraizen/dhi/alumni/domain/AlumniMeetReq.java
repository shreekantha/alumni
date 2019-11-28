package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.heraizen.dhi.alumni.domain.enumeration.AlumniMeetReqStatus;

/**
 * The AlumniMeetRequest entity.\n@author Shreekantha
 */
@Document(collection = "alumni_meet_req")
public class AlumniMeetReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("about_me")
    private String aboutMe;

    @Field("description")
    private String description;

    @Field("document")
    private byte[] document;

    @Field("document_content_type")
    private String documentContentType;

    @Field("status")
    private AlumniMeetReqStatus status;

    @DBRef
    @Field("requestBy")
    @JsonIgnoreProperties("alumniMeetReqs")
    private User requestBy;

    @DBRef
    @Field("requestTo")
    @JsonIgnoreProperties("alumniMeetReqs")
    private User requestTo;

    @DBRef
    @Field("topic")
    @JsonIgnoreProperties("alumniMeetReqs")
    private MeetReqTopic topic;

    @DBRef
    @Field("aspiredRole")
    @JsonIgnoreProperties("alumniMeetReqs")
    private AspiredRole aspiredRole;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public AlumniMeetReq aboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getDescription() {
        return description;
    }

    public AlumniMeetReq description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDocument() {
        return document;
    }

    public AlumniMeetReq document(byte[] document) {
        this.document = document;
        return this;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getDocumentContentType() {
        return documentContentType;
    }

    public AlumniMeetReq documentContentType(String documentContentType) {
        this.documentContentType = documentContentType;
        return this;
    }

    public void setDocumentContentType(String documentContentType) {
        this.documentContentType = documentContentType;
    }

    public AlumniMeetReqStatus getStatus() {
        return status;
    }

    public AlumniMeetReq status(AlumniMeetReqStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AlumniMeetReqStatus status) {
        this.status = status;
    }

    public User getRequestBy() {
        return requestBy;
    }

    public AlumniMeetReq requestBy(User user) {
        this.requestBy = user;
        return this;
    }

    public void setRequestBy(User user) {
        this.requestBy = user;
    }

    public User getRequestTo() {
        return requestTo;
    }

    public AlumniMeetReq requestTo(User user) {
        this.requestTo = user;
        return this;
    }

    public void setRequestTo(User user) {
        this.requestTo = user;
    }

    public MeetReqTopic getTopic() {
        return topic;
    }

    public AlumniMeetReq topic(MeetReqTopic meetReqTopic) {
        this.topic = meetReqTopic;
        return this;
    }

    public void setTopic(MeetReqTopic meetReqTopic) {
        this.topic = meetReqTopic;
    }

    public AspiredRole getAspiredRole() {
        return aspiredRole;
    }

    public AlumniMeetReq aspiredRole(AspiredRole aspiredRole) {
        this.aspiredRole = aspiredRole;
        return this;
    }

    public void setAspiredRole(AspiredRole aspiredRole) {
        this.aspiredRole = aspiredRole;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlumniMeetReq)) {
            return false;
        }
        return id != null && id.equals(((AlumniMeetReq) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AlumniMeetReq{" +
            "id=" + getId() +
            ", aboutMe='" + getAboutMe() + "'" +
            ", description='" + getDescription() + "'" +
            ", document='" + getDocument() + "'" +
            ", documentContentType='" + getDocumentContentType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
