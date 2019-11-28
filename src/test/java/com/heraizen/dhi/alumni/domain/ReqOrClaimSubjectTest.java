package com.heraizen.dhi.alumni.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class ReqOrClaimSubjectTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReqOrClaimSubject.class);
        ReqOrClaimSubject reqOrClaimSubject1 = new ReqOrClaimSubject();
        reqOrClaimSubject1.setId("id1");
        ReqOrClaimSubject reqOrClaimSubject2 = new ReqOrClaimSubject();
        reqOrClaimSubject2.setId(reqOrClaimSubject1.getId());
        assertThat(reqOrClaimSubject1).isEqualTo(reqOrClaimSubject2);
        reqOrClaimSubject2.setId("id2");
        assertThat(reqOrClaimSubject1).isNotEqualTo(reqOrClaimSubject2);
        reqOrClaimSubject1.setId(null);
        assertThat(reqOrClaimSubject1).isNotEqualTo(reqOrClaimSubject2);
    }
}
