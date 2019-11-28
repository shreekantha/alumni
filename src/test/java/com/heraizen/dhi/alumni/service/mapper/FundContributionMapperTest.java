package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FundContributionMapperTest {

    private FundContributionMapper fundContributionMapper;

    @BeforeEach
    public void setUp() {
        fundContributionMapper = new FundContributionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(fundContributionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fundContributionMapper.fromId(null)).isNull();
    }
}
