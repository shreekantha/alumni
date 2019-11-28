package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsCourseTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsCourseTopic.class);
        PdsCourseTopic pdsCourseTopic1 = new PdsCourseTopic();
        pdsCourseTopic1.setId("id1");
        PdsCourseTopic pdsCourseTopic2 = new PdsCourseTopic();
        pdsCourseTopic2.setId(pdsCourseTopic1.getId());
        assertThat(pdsCourseTopic1).isEqualTo(pdsCourseTopic2);
        pdsCourseTopic2.setId("id2");
        assertThat(pdsCourseTopic1).isNotEqualTo(pdsCourseTopic2);
        pdsCourseTopic1.setId(null);
        assertThat(pdsCourseTopic1).isNotEqualTo(pdsCourseTopic2);
    }
}
