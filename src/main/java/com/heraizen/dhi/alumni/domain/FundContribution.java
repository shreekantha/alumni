package com.heraizen.dhi.alumni.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.heraizen.dhi.alumni.domain.enumeration.Currency;

/**
 * The FundContribution entity.\n@author Shreekantha
 */
@Document(collection = "fund_contribution")
public class FundContribution extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("currency")
    private Currency currency;

    @NotNull
    @Field("contr_amount")
    private Double contrAmount;

    @Field("contr_date")
    private Instant contrDate;

    @DBRef
    @Field("contibutor")
    @JsonIgnoreProperties("fundContributions")
    private User contibutor;

    @DBRef
    @Field("fundraiser")
    @JsonIgnoreProperties("fundContributions")
    private Fundraiser fundraiser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public FundContribution currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getContrAmount() {
        return contrAmount;
    }

    public FundContribution contrAmount(Double contrAmount) {
        this.contrAmount = contrAmount;
        return this;
    }

    public void setContrAmount(Double contrAmount) {
        this.contrAmount = contrAmount;
    }

    public Instant getContrDate() {
        return contrDate;
    }

    public FundContribution contrDate(Instant contrDate) {
        this.contrDate = contrDate;
        return this;
    }

    public void setContrDate(Instant contrDate) {
        this.contrDate = contrDate;
    }

    public User getContibutor() {
        return contibutor;
    }

    public FundContribution contibutor(User user) {
        this.contibutor = user;
        return this;
    }

    public void setContibutor(User user) {
        this.contibutor = user;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public FundContribution fundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
        return this;
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundContribution)) {
            return false;
        }
        return id != null && id.equals(((FundContribution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FundContribution{" +
            "id=" + getId() +
            ", currency='" + getCurrency() + "'" +
            ", contrAmount=" + getContrAmount() +
            ", contrDate='" + getContrDate() + "'" +
            "}";
    }
}
