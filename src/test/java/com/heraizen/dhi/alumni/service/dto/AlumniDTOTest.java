package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniDTO.class);
        AlumniDTO alumniDTO1 = new AlumniDTO();
        alumniDTO1.setId("id1");
        AlumniDTO alumniDTO2 = new AlumniDTO();
        assertThat(alumniDTO1).isNotEqualTo(alumniDTO2);
        alumniDTO2.setId(alumniDTO1.getId());
        assertThat(alumniDTO1).isEqualTo(alumniDTO2);
        alumniDTO2.setId("id2");
        assertThat(alumniDTO1).isNotEqualTo(alumniDTO2);
        alumniDTO1.setId(null);
        assertThat(alumniDTO1).isNotEqualTo(alumniDTO2);
    }
}
