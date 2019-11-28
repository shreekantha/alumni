package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AlumniMeetReqMapperTest {

    private AlumniMeetReqMapper alumniMeetReqMapper;

    @BeforeEach
    public void setUp() {
        alumniMeetReqMapper = new AlumniMeetReqMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(alumniMeetReqMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(alumniMeetReqMapper.fromId(null)).isNull();
    }
}
