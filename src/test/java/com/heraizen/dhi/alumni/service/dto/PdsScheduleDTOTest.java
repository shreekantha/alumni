package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsScheduleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsScheduleDTO.class);
        PdsScheduleDTO pdsScheduleDTO1 = new PdsScheduleDTO();
        pdsScheduleDTO1.setId("id1");
        PdsScheduleDTO pdsScheduleDTO2 = new PdsScheduleDTO();
        assertThat(pdsScheduleDTO1).isNotEqualTo(pdsScheduleDTO2);
        pdsScheduleDTO2.setId(pdsScheduleDTO1.getId());
        assertThat(pdsScheduleDTO1).isEqualTo(pdsScheduleDTO2);
        pdsScheduleDTO2.setId("id2");
        assertThat(pdsScheduleDTO1).isNotEqualTo(pdsScheduleDTO2);
        pdsScheduleDTO1.setId(null);
        assertThat(pdsScheduleDTO1).isNotEqualTo(pdsScheduleDTO2);
    }
}
