package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FundraiserMapperTest {

    private FundraiserMapper fundraiserMapper;

    @BeforeEach
    public void setUp() {
        fundraiserMapper = new FundraiserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(fundraiserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fundraiserMapper.fromId(null)).isNull();
    }
}
