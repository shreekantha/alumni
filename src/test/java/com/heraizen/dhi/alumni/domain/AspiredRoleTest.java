package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AspiredRoleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AspiredRole.class);
        AspiredRole aspiredRole1 = new AspiredRole();
        aspiredRole1.setId("id1");
        AspiredRole aspiredRole2 = new AspiredRole();
        aspiredRole2.setId(aspiredRole1.getId());
        assertThat(aspiredRole1).isEqualTo(aspiredRole2);
        aspiredRole2.setId("id2");
        assertThat(aspiredRole1).isNotEqualTo(aspiredRole2);
        aspiredRole1.setId(null);
        assertThat(aspiredRole1).isNotEqualTo(aspiredRole2);
    }
}
