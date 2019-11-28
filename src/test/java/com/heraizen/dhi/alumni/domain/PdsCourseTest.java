package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsCourseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsCourse.class);
        PdsCourse pdsCourse1 = new PdsCourse();
        pdsCourse1.setId("id1");
        PdsCourse pdsCourse2 = new PdsCourse();
        pdsCourse2.setId(pdsCourse1.getId());
        assertThat(pdsCourse1).isEqualTo(pdsCourse2);
        pdsCourse2.setId("id2");
        assertThat(pdsCourse1).isNotEqualTo(pdsCourse2);
        pdsCourse1.setId(null);
        assertThat(pdsCourse1).isNotEqualTo(pdsCourse2);
    }
}
