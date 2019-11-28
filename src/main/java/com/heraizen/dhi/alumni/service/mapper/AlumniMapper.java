package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.AlumniDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Alumni} and its DTO {@link AlumniDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AlumniMapper extends EntityMapper<AlumniDTO, Alumni> {



    default Alumni fromId(String id) {
        if (id == null) {
            return null;
        }
        Alumni alumni = new Alumni();
        alumni.setId(id);
        return alumni;
    }
}
