package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProfDevServiceMapperTest {

    private ProfDevServiceMapper profDevServiceMapper;

    @BeforeEach
    public void setUp() {
        profDevServiceMapper = new ProfDevServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(profDevServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(profDevServiceMapper.fromId(null)).isNull();
    }
}
