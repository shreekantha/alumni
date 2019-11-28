package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.heraizen.dhi.alumni.domain.enumeration.Currency;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.FundContribution} entity.
 */
@ApiModel(description = "The FundContribution entity.\n@author Shreekantha")
public class FundContributionDTO implements Serializable {

    private String id;

    @NotNull
    private Currency currency;

    @NotNull
    private Double contrAmount;

    private Instant contrDate;


    private String contibutorId;

    private String contibutorFirstName;

    private String fundraiserId;

    private String fundraiserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getContrAmount() {
        return contrAmount;
    }

    public void setContrAmount(Double contrAmount) {
        this.contrAmount = contrAmount;
    }

    public Instant getContrDate() {
        return contrDate;
    }

    public void setContrDate(Instant contrDate) {
        this.contrDate = contrDate;
    }

    public String getContibutorId() {
        return contibutorId;
    }

    public void setContibutorId(String userId) {
        this.contibutorId = userId;
    }

    public String getContibutorFirstName() {
        return contibutorFirstName;
    }

    public void setContibutorFirstName(String userFirstName) {
        this.contibutorFirstName = userFirstName;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getFundraiserName() {
        return fundraiserName;
    }

    public void setFundraiserName(String fundraiserName) {
        this.fundraiserName = fundraiserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FundContributionDTO fundContributionDTO = (FundContributionDTO) o;
        if (fundContributionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fundContributionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FundContributionDTO{" +
            "id=" + getId() +
            ", currency='" + getCurrency() + "'" +
            ", contrAmount=" + getContrAmount() +
            ", contrDate='" + getContrDate() + "'" +
            ", contibutor=" + getContibutorId() +
            ", contibutor='" + getContibutorFirstName() + "'" +
            ", fundraiser=" + getFundraiserId() +
            ", fundraiser='" + getFundraiserName() + "'" +
            "}";
    }
}
