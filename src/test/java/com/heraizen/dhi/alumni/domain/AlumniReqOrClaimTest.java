package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniReqOrClaimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniReqOrClaim.class);
        AlumniReqOrClaim alumniReqOrClaim1 = new AlumniReqOrClaim();
        alumniReqOrClaim1.setId("id1");
        AlumniReqOrClaim alumniReqOrClaim2 = new AlumniReqOrClaim();
        alumniReqOrClaim2.setId(alumniReqOrClaim1.getId());
        assertThat(alumniReqOrClaim1).isEqualTo(alumniReqOrClaim2);
        alumniReqOrClaim2.setId("id2");
        assertThat(alumniReqOrClaim1).isNotEqualTo(alumniReqOrClaim2);
        alumniReqOrClaim1.setId(null);
        assertThat(alumniReqOrClaim1).isNotEqualTo(alumniReqOrClaim2);
    }
}
