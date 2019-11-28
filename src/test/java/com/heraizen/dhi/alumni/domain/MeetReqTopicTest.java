package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class MeetReqTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetReqTopic.class);
        MeetReqTopic meetReqTopic1 = new MeetReqTopic();
        meetReqTopic1.setId("id1");
        MeetReqTopic meetReqTopic2 = new MeetReqTopic();
        meetReqTopic2.setId(meetReqTopic1.getId());
        assertThat(meetReqTopic1).isEqualTo(meetReqTopic2);
        meetReqTopic2.setId("id2");
        assertThat(meetReqTopic1).isNotEqualTo(meetReqTopic2);
        meetReqTopic1.setId(null);
        assertThat(meetReqTopic1).isNotEqualTo(meetReqTopic2);
    }
}
