package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class FundContributionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundContribution.class);
        FundContribution fundContribution1 = new FundContribution();
        fundContribution1.setId("id1");
        FundContribution fundContribution2 = new FundContribution();
        fundContribution2.setId(fundContribution1.getId());
        assertThat(fundContribution1).isEqualTo(fundContribution2);
        fundContribution2.setId("id2");
        assertThat(fundContribution1).isNotEqualTo(fundContribution2);
        fundContribution1.setId(null);
        assertThat(fundContribution1).isNotEqualTo(fundContribution2);
    }
}
