package com.heraizen.dhi.alumni.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.heraizen.dhi.alumni.domain.enumeration.FundraiseStatus;

/**
 * A DTO for the {@link com.heraizen.dhi.alumni.domain.Fundraiser} entity.
 */
@ApiModel(description = "The Fundraiser entity.\n@author Shreekantha")
public class FundraiserDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double targetAmount;

    private Double collectedAmount;

    private LocalDate lastDateToContribute;

    private FundraiseStatus status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDate getLastDateToContribute() {
        return lastDateToContribute;
    }

    public void setLastDateToContribute(LocalDate lastDateToContribute) {
        this.lastDateToContribute = lastDateToContribute;
    }

    public FundraiseStatus getStatus() {
        return status;
    }

    public void setStatus(FundraiseStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FundraiserDTO fundraiserDTO = (FundraiserDTO) o;
        if (fundraiserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fundraiserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FundraiserDTO{" +
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
