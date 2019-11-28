package com.heraizen.dhi.alumni.service.mapper;

import com.heraizen.dhi.alumni.domain.*;
import com.heraizen.dhi.alumni.service.dto.DepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {DegreeMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

    @Mapping(source = "degree.id", target = "degreeId")
    @Mapping(source = "degree.name", target = "degreeName")
    DepartmentDTO toDto(Department department);

    @Mapping(source = "degreeId", target = "degree")
    Department toEntity(DepartmentDTO departmentDTO);

    default Department fromId(String id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setId(id);
        return department;
    }
}
