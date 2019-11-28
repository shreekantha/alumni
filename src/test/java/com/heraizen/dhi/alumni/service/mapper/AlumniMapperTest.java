package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AlumniMapperTest {

    private AlumniMapper alumniMapper;

    @BeforeEach
    public void setUp() {
        alumniMapper = new AlumniMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(alumniMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(alumniMapper.fromId(null)).isNull();
    }
}
