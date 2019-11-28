package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniReqOrClaimDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniReqOrClaimDTO.class);
        AlumniReqOrClaimDTO alumniReqOrClaimDTO1 = new AlumniReqOrClaimDTO();
        alumniReqOrClaimDTO1.setId("id1");
        AlumniReqOrClaimDTO alumniReqOrClaimDTO2 = new AlumniReqOrClaimDTO();
        assertThat(alumniReqOrClaimDTO1).isNotEqualTo(alumniReqOrClaimDTO2);
        alumniReqOrClaimDTO2.setId(alumniReqOrClaimDTO1.getId());
        assertThat(alumniReqOrClaimDTO1).isEqualTo(alumniReqOrClaimDTO2);
        alumniReqOrClaimDTO2.setId("id2");
        assertThat(alumniReqOrClaimDTO1).isNotEqualTo(alumniReqOrClaimDTO2);
        alumniReqOrClaimDTO1.setId(null);
        assertThat(alumniReqOrClaimDTO1).isNotEqualTo(alumniReqOrClaimDTO2);
    }
}
