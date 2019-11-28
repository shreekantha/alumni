package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.PdsCourseTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PdsCourseTopic} and its DTO {@link PdsCourseTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {PdsCourseMapper.class})
public interface PdsCourseTopicMapper extends EntityMapper<PdsCourseTopicDTO, PdsCourseTopic> {

    @Mapping(source = "pdsCourse.id", target = "pdsCourseId")
    @Mapping(source = "pdsCourse.courseName", target = "pdsCourseCourseName")
    PdsCourseTopicDTO toDto(PdsCourseTopic pdsCourseTopic);

    @Mapping(source = "pdsCourseId", target = "pdsCourse")
    PdsCourseTopic toEntity(PdsCourseTopicDTO pdsCourseTopicDTO);

    default PdsCourseTopic fromId(String id) {
        if (id == null) {
            return null;
        }
        PdsCourseTopic pdsCourseTopic = new PdsCourseTopic();
        pdsCourseTopic.setId(id);
        return pdsCourseTopic;
    }
}
