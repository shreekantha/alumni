package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.DegreeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Degree} and its DTO {@link DegreeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DegreeMapper extends EntityMapper<DegreeDTO, Degree> {


    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "removeDepartment", ignore = true)
    Degree toEntity(DegreeDTO degreeDTO);

    default Degree fromId(String id) {
        if (id == null) {
            return null;
        }
        Degree degree = new Degree();
        degree.setId(id);
        return degree;
    }
}
