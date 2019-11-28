package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AspiredRoleMapperTest {

    private AspiredRoleMapper aspiredRoleMapper;

    @BeforeEach
    public void setUp() {
        aspiredRoleMapper = new AspiredRoleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(aspiredRoleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(aspiredRoleMapper.fromId(null)).isNull();
    }
}
