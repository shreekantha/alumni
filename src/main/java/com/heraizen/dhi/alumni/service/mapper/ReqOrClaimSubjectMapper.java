package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.ReqOrClaimSubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReqOrClaimSubject} and its DTO {@link ReqOrClaimSubjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReqOrClaimSubjectMapper extends EntityMapper<ReqOrClaimSubjectDTO, ReqOrClaimSubject> {


    @Mapping(target = "alumniReqOrClaims", ignore = true)
    @Mapping(target = "removeAlumniReqOrClaim", ignore = true)
    ReqOrClaimSubject toEntity(ReqOrClaimSubjectDTO reqOrClaimSubjectDTO);

    default ReqOrClaimSubject fromId(String id) {
        if (id == null) {
            return null;
        }
        ReqOrClaimSubject reqOrClaimSubject = new ReqOrClaimSubject();
        reqOrClaimSubject.setId(id);
        return reqOrClaimSubject;
    }
}
