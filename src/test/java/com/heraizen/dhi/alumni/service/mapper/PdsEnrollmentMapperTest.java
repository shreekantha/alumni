package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PdsEnrollmentMapperTest {

    private PdsEnrollmentMapper pdsEnrollmentMapper;

    @BeforeEach
    public void setUp() {
        pdsEnrollmentMapper = new PdsEnrollmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(pdsEnrollmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pdsEnrollmentMapper.fromId(null)).isNull();
    }
}
