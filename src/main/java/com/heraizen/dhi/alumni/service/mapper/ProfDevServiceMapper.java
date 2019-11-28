package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.ProfDevServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProfDevService} and its DTO {@link ProfDevServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfDevServiceMapper extends EntityMapper<ProfDevServiceDTO, ProfDevService> {


    @Mapping(target = "pdsCourses", ignore = true)
    @Mapping(target = "removePdsCourse", ignore = true)
    @Mapping(target = "pdsSchedules", ignore = true)
    @Mapping(target = "removePdsSchedule", ignore = true)
    ProfDevService toEntity(ProfDevServiceDTO profDevServiceDTO);

    default ProfDevService fromId(String id) {
        if (id == null) {
            return null;
        }
        ProfDevService profDevService = new ProfDevService();
        profDevService.setId(id);
        return profDevService;
    }
}
