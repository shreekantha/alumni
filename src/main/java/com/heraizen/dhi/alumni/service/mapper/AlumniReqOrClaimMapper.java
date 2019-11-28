package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AlumniReqOrClaim} and its DTO {@link AlumniReqOrClaimDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ReqOrClaimSubjectMapper.class})
public interface AlumniReqOrClaimMapper extends EntityMapper<AlumniReqOrClaimDTO, AlumniReqOrClaim> {

    @Mapping(source = "requestBy.id", target = "requestById")
    @Mapping(source = "requestBy.firstName", target = "requestByFirstName")
    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "assignee.firstName", target = "assigneeFirstName")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.name", target = "subjectName")
    AlumniReqOrClaimDTO toDto(AlumniReqOrClaim alumniReqOrClaim);

    @Mapping(source = "requestById", target = "requestBy")
    @Mapping(source = "assigneeId", target = "assignee")
    @Mapping(source = "subjectId", target = "subject")
    AlumniReqOrClaim toEntity(AlumniReqOrClaimDTO alumniReqOrClaimDTO);

    default AlumniReqOrClaim fromId(String id) {
        if (id == null) {
            return null;
        }
        AlumniReqOrClaim alumniReqOrClaim = new AlumniReqOrClaim();
        alumniReqOrClaim.setId(id);
        return alumniReqOrClaim;
    }
}
