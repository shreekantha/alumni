package com.heraizen.dhi.alumni.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The ReqOrClaimSubject entity.\n@author Shreekantha
 */
@Document(collection = "req_or_claim_subject")
public class ReqOrClaimSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("decsription")
    private String decsription;

    @DBRef
    @Field("alumniReqOrClaim")
    private Set<AlumniReqOrClaim> alumniReqOrClaims = new HashSet<>();

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

    public ReqOrClaimSubject name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecsription() {
        return decsription;
    }

    public ReqOrClaimSubject decsription(String decsription) {
        this.decsription = decsription;
        return this;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public Set<AlumniReqOrClaim> getAlumniReqOrClaims() {
        return alumniReqOrClaims;
    }

    public ReqOrClaimSubject alumniReqOrClaims(Set<AlumniReqOrClaim> alumniReqOrClaims) {
        this.alumniReqOrClaims = alumniReqOrClaims;
        return this;
    }

    public ReqOrClaimSubject addAlumniReqOrClaim(AlumniReqOrClaim alumniReqOrClaim) {
        this.alumniReqOrClaims.add(alumniReqOrClaim);
        alumniReqOrClaim.setSubject(this);
        return this;
    }

    public ReqOrClaimSubject removeAlumniReqOrClaim(AlumniReqOrClaim alumniReqOrClaim) {
        this.alumniReqOrClaims.remove(alumniReqOrClaim);
        alumniReqOrClaim.setSubject(null);
        return this;
    }

    public void setAlumniReqOrClaims(Set<AlumniReqOrClaim> alumniReqOrClaims) {
        this.alumniReqOrClaims = alumniReqOrClaims;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReqOrClaimSubject)) {
            return false;
        }
        return id != null && id.equals(((ReqOrClaimSubject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReqOrClaimSubject{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", decsription='" + getDecsription() + "'" +
            "}";
    }
}
