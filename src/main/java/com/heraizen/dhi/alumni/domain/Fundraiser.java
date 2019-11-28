package com.heraizen.dhi.alumni.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.heraizen.dhi.alumni.domain.enumeration.FundraiseStatus;

/**
 * The Fundraiser entity.\n@author Shreekantha
 */
@Document(collection = "fundraiser")
public class Fundraiser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @NotNull
    @Field("target_amount")
    private Double targetAmount;

    @Field("collected_amount")
    private Double collectedAmount;

    @Field("last_date_to_contribute")
    private LocalDate lastDateToContribute;

    @Field("status")
    private FundraiseStatus status;

    @DBRef
    @Field("fundContribution")
    private Set<FundContribution> fundContributions = new HashSet<>();

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

    public Fundraiser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Fundraiser description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public Fundraiser targetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
        return this;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public Fundraiser collectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
        return this;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDate getLastDateToContribute() {
        return lastDateToContribute;
    }

    public Fundraiser lastDateToContribute(LocalDate lastDateToContribute) {
        this.lastDateToContribute = lastDateToContribute;
        return this;
    }

    public void setLastDateToContribute(LocalDate lastDateToContribute) {
        this.lastDateToContribute = lastDateToContribute;
    }

    public FundraiseStatus getStatus() {
        return status;
    }

    public Fundraiser status(FundraiseStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(FundraiseStatus status) {
        this.status = status;
    }

    public Set<FundContribution> getFundContributions() {
        return fundContributions;
    }

    public Fundraiser fundContributions(Set<FundContribution> fundContributions) {
        this.fundContributions = fundContributions;
        return this;
    }

    public Fundraiser addFundContribution(FundContribution fundContribution) {
        this.fundContributions.add(fundContribution);
        fundContribution.setFundraiser(this);
        return this;
    }

    public Fundraiser removeFundContribution(FundContribution fundContribution) {
        this.fundContributions.remove(fundContribution);
        fundContribution.setFundraiser(null);
        return this;
    }

    public void setFundContributions(Set<FundContribution> fundContributions) {
        this.fundContributions = fundContributions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fundraiser)) {
            return false;
        }
        return id != null && id.equals(((Fundraiser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Fundraiser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", targetAmount=" + getTargetAmount() +
            ", collectedAmount=" + getCollectedAmount() +
            ", lastDateToContribute='" + getLastDateToContribute() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
