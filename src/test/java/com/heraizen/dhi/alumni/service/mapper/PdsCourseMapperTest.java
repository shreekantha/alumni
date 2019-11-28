package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PdsCourseMapperTest {

    private PdsCourseMapper pdsCourseMapper;

    @BeforeEach
    public void setUp() {
        pdsCourseMapper = new PdsCourseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(pdsCourseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pdsCourseMapper.fromId(null)).isNull();
    }
}
