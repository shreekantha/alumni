package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsCourseTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsCourseTopicDTO.class);
        PdsCourseTopicDTO pdsCourseTopicDTO1 = new PdsCourseTopicDTO();
        pdsCourseTopicDTO1.setId("id1");
        PdsCourseTopicDTO pdsCourseTopicDTO2 = new PdsCourseTopicDTO();
        assertThat(pdsCourseTopicDTO1).isNotEqualTo(pdsCourseTopicDTO2);
        pdsCourseTopicDTO2.setId(pdsCourseTopicDTO1.getId());
        assertThat(pdsCourseTopicDTO1).isEqualTo(pdsCourseTopicDTO2);
        pdsCourseTopicDTO2.setId("id2");
        assertThat(pdsCourseTopicDTO1).isNotEqualTo(pdsCourseTopicDTO2);
        pdsCourseTopicDTO1.setId(null);
        assertThat(pdsCourseTopicDTO1).isNotEqualTo(pdsCourseTopicDTO2);
    }
}
