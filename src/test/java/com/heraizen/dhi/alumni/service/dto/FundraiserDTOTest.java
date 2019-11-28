package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class FundraiserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundraiserDTO.class);
        FundraiserDTO fundraiserDTO1 = new FundraiserDTO();
        fundraiserDTO1.setId("id1");
        FundraiserDTO fundraiserDTO2 = new FundraiserDTO();
        assertThat(fundraiserDTO1).isNotEqualTo(fundraiserDTO2);
        fundraiserDTO2.setId(fundraiserDTO1.getId());
        assertThat(fundraiserDTO1).isEqualTo(fundraiserDTO2);
        fundraiserDTO2.setId("id2");
        assertThat(fundraiserDTO1).isNotEqualTo(fundraiserDTO2);
        fundraiserDTO1.setId(null);
        assertThat(fundraiserDTO1).isNotEqualTo(fundraiserDTO2);
    }
}
