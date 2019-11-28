package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class AspiredRoleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AspiredRoleDTO.class);
        AspiredRoleDTO aspiredRoleDTO1 = new AspiredRoleDTO();
        aspiredRoleDTO1.setId("id1");
        AspiredRoleDTO aspiredRoleDTO2 = new AspiredRoleDTO();
        assertThat(aspiredRoleDTO1).isNotEqualTo(aspiredRoleDTO2);
        aspiredRoleDTO2.setId(aspiredRoleDTO1.getId());
        assertThat(aspiredRoleDTO1).isEqualTo(aspiredRoleDTO2);
        aspiredRoleDTO2.setId("id2");
        assertThat(aspiredRoleDTO1).isNotEqualTo(aspiredRoleDTO2);
        aspiredRoleDTO1.setId(null);
        assertThat(aspiredRoleDTO1).isNotEqualTo(aspiredRoleDTO2);
    }
}
