package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsEnrollmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsEnrollmentDTO.class);
        PdsEnrollmentDTO pdsEnrollmentDTO1 = new PdsEnrollmentDTO();
        pdsEnrollmentDTO1.setId("id1");
        PdsEnrollmentDTO pdsEnrollmentDTO2 = new PdsEnrollmentDTO();
        assertThat(pdsEnrollmentDTO1).isNotEqualTo(pdsEnrollmentDTO2);
        pdsEnrollmentDTO2.setId(pdsEnrollmentDTO1.getId());
        assertThat(pdsEnrollmentDTO1).isEqualTo(pdsEnrollmentDTO2);
        pdsEnrollmentDTO2.setId("id2");
        assertThat(pdsEnrollmentDTO1).isNotEqualTo(pdsEnrollmentDTO2);
        pdsEnrollmentDTO1.setId(null);
        assertThat(pdsEnrollmentDTO1).isNotEqualTo(pdsEnrollmentDTO2);
    }
}
