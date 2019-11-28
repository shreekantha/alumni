package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AlumniTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alumni.class);
        Alumni alumni1 = new Alumni();
        alumni1.setId("id1");
        Alumni alumni2 = new Alumni();
        alumni2.setId(alumni1.getId());
        assertThat(alumni1).isEqualTo(alumni2);
        alumni2.setId("id2");
        assertThat(alumni1).isNotEqualTo(alumni2);
        alumni1.setId(null);
        assertThat(alumni1).isNotEqualTo(alumni2);
    }
}
