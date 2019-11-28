package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniMeetReqTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniMeetReq.class);
        AlumniMeetReq alumniMeetReq1 = new AlumniMeetReq();
        alumniMeetReq1.setId("id1");
        AlumniMeetReq alumniMeetReq2 = new AlumniMeetReq();
        alumniMeetReq2.setId(alumniMeetReq1.getId());
        assertThat(alumniMeetReq1).isEqualTo(alumniMeetReq2);
        alumniMeetReq2.setId("id2");
        assertThat(alumniMeetReq1).isNotEqualTo(alumniMeetReq2);
        alumniMeetReq1.setId(null);
        assertThat(alumniMeetReq1).isNotEqualTo(alumniMeetReq2);
    }
}
