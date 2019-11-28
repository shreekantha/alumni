package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class ProfDevServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfDevService.class);
        ProfDevService profDevService1 = new ProfDevService();
        profDevService1.setId("id1");
        ProfDevService profDevService2 = new ProfDevService();
        profDevService2.setId(profDevService1.getId());
        assertThat(profDevService1).isEqualTo(profDevService2);
        profDevService2.setId("id2");
        assertThat(profDevService1).isNotEqualTo(profDevService2);
        profDevService1.setId(null);
        assertThat(profDevService1).isNotEqualTo(profDevService2);
    }
}
