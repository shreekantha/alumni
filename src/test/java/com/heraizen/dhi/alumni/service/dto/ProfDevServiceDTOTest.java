package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class ProfDevServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfDevServiceDTO.class);
        ProfDevServiceDTO profDevServiceDTO1 = new ProfDevServiceDTO();
        profDevServiceDTO1.setId("id1");
        ProfDevServiceDTO profDevServiceDTO2 = new ProfDevServiceDTO();
        assertThat(profDevServiceDTO1).isNotEqualTo(profDevServiceDTO2);
        profDevServiceDTO2.setId(profDevServiceDTO1.getId());
        assertThat(profDevServiceDTO1).isEqualTo(profDevServiceDTO2);
        profDevServiceDTO2.setId("id2");
        assertThat(profDevServiceDTO1).isNotEqualTo(profDevServiceDTO2);
        profDevServiceDTO1.setId(null);
        assertThat(profDevServiceDTO1).isNotEqualTo(profDevServiceDTO2);
    }
}
