package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PdsCourseTopicMapperTest {

    private PdsCourseTopicMapper pdsCourseTopicMapper;

    @BeforeEach
    public void setUp() {
        pdsCourseTopicMapper = new PdsCourseTopicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(pdsCourseTopicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pdsCourseTopicMapper.fromId(null)).isNull();
    }
}
