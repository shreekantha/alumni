package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MeetReqTopicMapperTest {

    private MeetReqTopicMapper meetReqTopicMapper;

    @BeforeEach
    public void setUp() {
        meetReqTopicMapper = new MeetReqTopicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(meetReqTopicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(meetReqTopicMapper.fromId(null)).isNull();
    }
}
