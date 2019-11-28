package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AlumniMeetReq} and its DTO {@link AlumniMeetReqDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, MeetReqTopicMapper.class, AspiredRoleMapper.class})
public interface AlumniMeetReqMapper extends EntityMapper<AlumniMeetReqDTO, AlumniMeetReq> {

    @Mapping(source = "requestBy.id", target = "requestById")
    @Mapping(source = "requestBy.firstName", target = "requestByFirstName")
    @Mapping(source = "requestTo.id", target = "requestToId")
    @Mapping(source = "requestTo.firstName", target = "requestToFirstName")
    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "aspiredRole.id", target = "aspiredRoleId")
    @Mapping(source = "aspiredRole.name", target = "aspiredRoleName")
    AlumniMeetReqDTO toDto(AlumniMeetReq alumniMeetReq);

    @Mapping(source = "requestById", target = "requestBy")
    @Mapping(source = "requestToId", target = "requestTo")
    @Mapping(source = "topicId", target = "topic")
    @Mapping(source = "aspiredRoleId", target = "aspiredRole")
    AlumniMeetReq toEntity(AlumniMeetReqDTO alumniMeetReqDTO);

    default AlumniMeetReq fromId(String id) {
        if (id == null) {
            return null;
        }
        AlumniMeetReq alumniMeetReq = new AlumniMeetReq();
        alumniMeetReq.setId(id);
        return alumniMeetReq;
    }
}
