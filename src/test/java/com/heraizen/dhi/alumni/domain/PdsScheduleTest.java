package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class PdsScheduleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdsSchedule.class);
        PdsSchedule pdsSchedule1 = new PdsSchedule();
        pdsSchedule1.setId("id1");
        PdsSchedule pdsSchedule2 = new PdsSchedule();
        pdsSchedule2.setId(pdsSchedule1.getId());
        assertThat(pdsSchedule1).isEqualTo(pdsSchedule2);
        pdsSchedule2.setId("id2");
        assertThat(pdsSchedule1).isNotEqualTo(pdsSchedule2);
        pdsSchedule1.setId(null);
        assertThat(pdsSchedule1).isNotEqualTo(pdsSchedule2);
    }
}
