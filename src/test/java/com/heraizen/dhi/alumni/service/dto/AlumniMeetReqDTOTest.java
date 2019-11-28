package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniMeetReqDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniMeetReqDTO.class);
        AlumniMeetReqDTO alumniMeetReqDTO1 = new AlumniMeetReqDTO();
        alumniMeetReqDTO1.setId("id1");
        AlumniMeetReqDTO alumniMeetReqDTO2 = new AlumniMeetReqDTO();
        assertThat(alumniMeetReqDTO1).isNotEqualTo(alumniMeetReqDTO2);
        alumniMeetReqDTO2.setId(alumniMeetReqDTO1.getId());
        assertThat(alumniMeetReqDTO1).isEqualTo(alumniMeetReqDTO2);
        alumniMeetReqDTO2.setId("id2");
        assertThat(alumniMeetReqDTO1).isNotEqualTo(alumniMeetReqDTO2);
        alumniMeetReqDTO1.setId(null);
        assertThat(alumniMeetReqDTO1).isNotEqualTo(alumniMeetReqDTO2);
    }
}
