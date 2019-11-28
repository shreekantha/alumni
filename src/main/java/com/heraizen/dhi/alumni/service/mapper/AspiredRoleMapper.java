package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.AspiredRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AspiredRole} and its DTO {@link AspiredRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AspiredRoleMapper extends EntityMapper<AspiredRoleDTO, AspiredRole> {


    @Mapping(target = "alumniMeetReqs", ignore = true)
    @Mapping(target = "removeAlumniMeetReq", ignore = true)
    AspiredRole toEntity(AspiredRoleDTO aspiredRoleDTO);

    default AspiredRole fromId(String id) {
        if (id == null) {
            return null;
        }
        AspiredRole aspiredRole = new AspiredRole();
        aspiredRole.setId(id);
        return aspiredRole;
    }
}
