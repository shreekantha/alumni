package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.PdsScheduleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PdsSchedule} and its DTO {@link PdsScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProfDevServiceMapper.class})
public interface PdsScheduleMapper extends EntityMapper<PdsScheduleDTO, PdsSchedule> {

    @Mapping(source = "profDevService.id", target = "profDevServiceId")
    @Mapping(source = "profDevService.name", target = "profDevServiceName")
    PdsScheduleDTO toDto(PdsSchedule pdsSchedule);

    @Mapping(target = "pdsEnrollments", ignore = true)
    @Mapping(target = "removePdsEnrollment", ignore = true)
    @Mapping(target = "removeUser", ignore = true)
    @Mapping(source = "profDevServiceId", target = "profDevService")
    PdsSchedule toEntity(PdsScheduleDTO pdsScheduleDTO);

    default PdsSchedule fromId(String id) {
        if (id == null) {
            return null;
        }
        PdsSchedule pdsSchedule = new PdsSchedule();
        pdsSchedule.setId(id);
        return pdsSchedule;
    }
}
