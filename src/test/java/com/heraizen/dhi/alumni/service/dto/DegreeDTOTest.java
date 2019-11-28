package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class DegreeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DegreeDTO.class);
        DegreeDTO degreeDTO1 = new DegreeDTO();
        degreeDTO1.setId("id1");
        DegreeDTO degreeDTO2 = new DegreeDTO();
        assertThat(degreeDTO1).isNotEqualTo(degreeDTO2);
        degreeDTO2.setId(degreeDTO1.getId());
        assertThat(degreeDTO1).isEqualTo(degreeDTO2);
        degreeDTO2.setId("id2");
        assertThat(degreeDTO1).isNotEqualTo(degreeDTO2);
        degreeDTO1.setId(null);
        assertThat(degreeDTO1).isNotEqualTo(degreeDTO2);
    }
}
