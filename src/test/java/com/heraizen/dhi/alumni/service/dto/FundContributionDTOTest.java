package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class FundContributionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundContributionDTO.class);
        FundContributionDTO fundContributionDTO1 = new FundContributionDTO();
        fundContributionDTO1.setId("id1");
        FundContributionDTO fundContributionDTO2 = new FundContributionDTO();
        assertThat(fundContributionDTO1).isNotEqualTo(fundContributionDTO2);
        fundContributionDTO2.setId(fundContributionDTO1.getId());
        assertThat(fundContributionDTO1).isEqualTo(fundContributionDTO2);
        fundContributionDTO2.setId("id2");
        assertThat(fundContributionDTO1).isNotEqualTo(fundContributionDTO2);
        fundContributionDTO1.setId(null);
        assertThat(fundContributionDTO1).isNotEqualTo(fundContributionDTO2);
    }
}
