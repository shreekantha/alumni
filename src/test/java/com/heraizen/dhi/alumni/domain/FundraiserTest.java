package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class FundraiserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fundraiser.class);
        Fundraiser fundraiser1 = new Fundraiser();
        fundraiser1.setId("id1");
        Fundraiser fundraiser2 = new Fundraiser();
        fundraiser2.setId(fundraiser1.getId());
        assertThat(fundraiser1).isEqualTo(fundraiser2);
        fundraiser2.setId("id2");
        assertThat(fundraiser1).isNotEqualTo(fundraiser2);
        fundraiser1.setId(null);
        assertThat(fundraiser1).isNotEqualTo(fundraiser2);
    }
}
