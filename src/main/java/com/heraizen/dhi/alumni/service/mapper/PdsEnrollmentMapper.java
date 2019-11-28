package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.PdsEnrollmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PdsEnrollment} and its DTO {@link PdsEnrollmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PdsScheduleMapper.class})
public interface PdsEnrollmentMapper extends EntityMapper<PdsEnrollmentDTO, PdsEnrollment> {

    @Mapping(source = "enrolledBy.id", target = "enrolledById")
    @Mapping(source = "enrolledBy.firstName", target = "enrolledByFirstName")
    @Mapping(source = "enrolledTo.id", target = "enrolledToId")
    PdsEnrollmentDTO toDto(PdsEnrollment pdsEnrollment);

    @Mapping(source = "enrolledById", target = "enrolledBy")
    @Mapping(source = "enrolledToId", target = "enrolledTo")
    PdsEnrollment toEntity(PdsEnrollmentDTO pdsEnrollmentDTO);

    default PdsEnrollment fromId(String id) {
        if (id == null) {
            return null;
        }
        PdsEnrollment pdsEnrollment = new PdsEnrollment();
        pdsEnrollment.setId(id);
        return pdsEnrollment;
    }
}
