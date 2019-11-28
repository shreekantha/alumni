package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.MeetReqTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MeetReqTopic} and its DTO {@link MeetReqTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MeetReqTopicMapper extends EntityMapper<MeetReqTopicDTO, MeetReqTopic> {


    @Mapping(target = "alumniMeetReqs", ignore = true)
    @Mapping(target = "removeAlumniMeetReq", ignore = true)
    MeetReqTopic toEntity(MeetReqTopicDTO meetReqTopicDTO);

    default MeetReqTopic fromId(String id) {
        if (id == null) {
            return null;
        }
        MeetReqTopic meetReqTopic = new MeetReqTopic();
        meetReqTopic.setId(id);
        return meetReqTopic;
    }
}
