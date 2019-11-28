package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PdsScheduleMapperTest {

    private PdsScheduleMapper pdsScheduleMapper;

    @BeforeEach
    public void setUp() {
        pdsScheduleMapper = new PdsScheduleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(pdsScheduleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pdsScheduleMapper.fromId(null)).isNull();
    }
}
