package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundContribution} and its DTO {@link FundContributionDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, FundraiserMapper.class})
public interface FundContributionMapper extends EntityMapper<FundContributionDTO, FundContribution> {

    @Mapping(source = "contibutor.id", target = "contibutorId")
    @Mapping(source = "contibutor.firstName", target = "contibutorFirstName")
    @Mapping(source = "fundraiser.id", target = "fundraiserId")
    @Mapping(source = "fundraiser.name", target = "fundraiserName")
    FundContributionDTO toDto(FundContribution fundContribution);

    @Mapping(source = "contibutorId", target = "contibutor")
    @Mapping(source = "fundraiserId", target = "fundraiser")
    FundContribution toEntity(FundContributionDTO fundContributionDTO);

    default FundContribution fromId(String id) {
        if (id == null) {
            return null;
        }
        FundContribution fundContribution = new FundContribution();
        fundContribution.setId(id);
        return fundContribution;
    }
}
