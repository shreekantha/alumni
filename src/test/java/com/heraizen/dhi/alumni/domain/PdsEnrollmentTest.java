package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsEnrollmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsEnrollment.class);
        PdsEnrollment pdsEnrollment1 = new PdsEnrollment();
        pdsEnrollment1.setId("id1");
        PdsEnrollment pdsEnrollment2 = new PdsEnrollment();
        pdsEnrollment2.setId(pdsEnrollment1.getId());
        assertThat(pdsEnrollment1).isEqualTo(pdsEnrollment2);
        pdsEnrollment2.setId("id2");
        assertThat(pdsEnrollment1).isNotEqualTo(pdsEnrollment2);
        pdsEnrollment1.setId(null);
        assertThat(pdsEnrollment1).isNotEqualTo(pdsEnrollment2);
    }
}
