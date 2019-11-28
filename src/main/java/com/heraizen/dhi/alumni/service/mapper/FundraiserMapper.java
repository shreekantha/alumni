package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.FundraiserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fundraiser} and its DTO {@link FundraiserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FundraiserMapper extends EntityMapper<FundraiserDTO, Fundraiser> {


    @Mapping(target = "fundContributions", ignore = true)
    @Mapping(target = "removeFundContribution", ignore = true)
    Fundraiser toEntity(FundraiserDTO fundraiserDTO);

    default Fundraiser fromId(String id) {
        if (id == null) {
            return null;
        }
        Fundraiser fundraiser = new Fundraiser();
        fundraiser.setId(id);
        return fundraiser;
    }
}
