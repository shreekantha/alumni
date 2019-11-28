package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.heraizen.dhi.alumni.domain.enumeration.AlumniMeetReqStatus;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.AlumniMeetReq} entity.
 */
@ApiModel(description = "The AlumniMeetRequest entity.\n@author Shreekantha")
public class AlumniMeetReqDTO implements Serializable {

    private String id;

    private String aboutMe;

    private String description;

    private byte[] document;

    private String documentContentType;
    private AlumniMeetReqStatus status;


    private String requestById;

    private String requestByFirstName;

    private String requestToId;

    private String requestToFirstName;

    private String topicId;

    private String topicName;

    private String aspiredRoleId;

    private String aspiredRoleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getDocumentContentType() {
        return documentContentType;
    }

    public void setDocumentContentType(String documentContentType) {
        this.documentContentType = documentContentType;
    }

    public AlumniMeetReqStatus getStatus() {
        return status;
    }

    public void setStatus(AlumniMeetReqStatus status) {
        this.status = status;
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

    public String getRequestToId() {
        return requestToId;
    }

    public void setRequestToId(String userId) {
        this.requestToId = userId;
    }

    public String getRequestToFirstName() {
        return requestToFirstName;
    }

    public void setRequestToFirstName(String userFirstName) {
        this.requestToFirstName = userFirstName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String meetReqTopicId) {
        this.topicId = meetReqTopicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String meetReqTopicName) {
        this.topicName = meetReqTopicName;
    }

    public String getAspiredRoleId() {
        return aspiredRoleId;
    }

    public void setAspiredRoleId(String aspiredRoleId) {
        this.aspiredRoleId = aspiredRoleId;
    }

    public String getAspiredRoleName() {
        return aspiredRoleName;
    }

    public void setAspiredRoleName(String aspiredRoleName) {
        this.aspiredRoleName = aspiredRoleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlumniMeetReqDTO alumniMeetReqDTO = (AlumniMeetReqDTO) o;
        if (alumniMeetReqDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alumniMeetReqDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlumniMeetReqDTO{" +
            "id=" + getId() +
            ", aboutMe='" + getAboutMe() + "'" +
            ", description='" + getDescription() + "'" +
            ", document='" + getDocument() + "'" +
            ", status='" + getStatus() + "'" +
            ", requestBy=" + getRequestById() +
            ", requestBy='" + getRequestByFirstName() + "'" +
            ", requestTo=" + getRequestToId() +
            ", requestTo='" + getRequestToFirstName() + "'" +
            ", topic=" + getTopicId() +
            ", topic='" + getTopicName() + "'" +
            ", aspiredRole=" + getAspiredRoleId() +
            ", aspiredRole='" + getAspiredRoleName() + "'" +
            "}";
    }
}
