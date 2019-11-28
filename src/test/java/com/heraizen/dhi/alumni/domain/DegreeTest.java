package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class DegreeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Degree.class);
        Degree degree1 = new Degree();
        degree1.setId("id1");
        Degree degree2 = new Degree();
        degree2.setId(degree1.getId());
        assertThat(degree1).isEqualTo(degree2);
        degree2.setId("id2");
        assertThat(degree1).isNotEqualTo(degree2);
        degree1.setId(null);
        assertThat(degree1).isNotEqualTo(degree2);
    }
}
