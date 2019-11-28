package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.PdsCourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PdsCourse} and its DTO {@link PdsCourseDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfDevServiceMapper.class})
public interface PdsCourseMapper extends EntityMapper<PdsCourseDTO, PdsCourse> {

    @Mapping(source = "profDevService.id", target = "profDevServiceId")
    @Mapping(source = "profDevService.name", target = "profDevServiceName")
    PdsCourseDTO toDto(PdsCourse pdsCourse);

    @Mapping(target = "pdsCourseTopics", ignore = true)
    @Mapping(target = "removePdsCourseTopic", ignore = true)
    @Mapping(source = "profDevServiceId", target = "profDevService")
    PdsCourse toEntity(PdsCourseDTO pdsCourseDTO);

    default PdsCourse fromId(String id) {
        if (id == null) {
            return null;
        }
        PdsCourse pdsCourse = new PdsCourse();
        pdsCourse.setId(id);
        return pdsCourse;
    }
}
