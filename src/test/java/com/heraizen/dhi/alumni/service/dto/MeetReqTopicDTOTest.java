package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class MeetReqTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetReqTopicDTO.class);
        MeetReqTopicDTO meetReqTopicDTO1 = new MeetReqTopicDTO();
        meetReqTopicDTO1.setId("id1");
        MeetReqTopicDTO meetReqTopicDTO2 = new MeetReqTopicDTO();
        assertThat(meetReqTopicDTO1).isNotEqualTo(meetReqTopicDTO2);
        meetReqTopicDTO2.setId(meetReqTopicDTO1.getId());
        assertThat(meetReqTopicDTO1).isEqualTo(meetReqTopicDTO2);
        meetReqTopicDTO2.setId("id2");
        assertThat(meetReqTopicDTO1).isNotEqualTo(meetReqTopicDTO2);
        meetReqTopicDTO1.setId(null);
        assertThat(meetReqTopicDTO1).isNotEqualTo(meetReqTopicDTO2);
    }
}
