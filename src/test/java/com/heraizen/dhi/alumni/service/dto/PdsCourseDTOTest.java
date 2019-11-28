package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsCourseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsCourseDTO.class);
        PdsCourseDTO pdsCourseDTO1 = new PdsCourseDTO();
        pdsCourseDTO1.setId("id1");
        PdsCourseDTO pdsCourseDTO2 = new PdsCourseDTO();
        assertThat(pdsCourseDTO1).isNotEqualTo(pdsCourseDTO2);
        pdsCourseDTO2.setId(pdsCourseDTO1.getId());
        assertThat(pdsCourseDTO1).isEqualTo(pdsCourseDTO2);
        pdsCourseDTO2.setId("id2");
        assertThat(pdsCourseDTO1).isNotEqualTo(pdsCourseDTO2);
        pdsCourseDTO1.setId(null);
        assertThat(pdsCourseDTO1).isNotEqualTo(pdsCourseDTO2);
    }
}
