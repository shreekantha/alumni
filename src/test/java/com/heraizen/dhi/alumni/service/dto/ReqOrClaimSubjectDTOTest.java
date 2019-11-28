package com.heraizen.dhi.alumni.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.heraizen.dhi.alumni.web.rest.TestUtil;

public class ReqOrClaimSubjectDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReqOrClaimSubjectDTO.class);
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO1 = new ReqOrClaimSubjectDTO();
        reqOrClaimSubjectDTO1.setId("id1");
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO2 = new ReqOrClaimSubjectDTO();
        assertThat(reqOrClaimSubjectDTO1).isNotEqualTo(reqOrClaimSubjectDTO2);
        reqOrClaimSubjectDTO2.setId(reqOrClaimSubjectDTO1.getId());
        assertThat(reqOrClaimSubjectDTO1).isEqualTo(reqOrClaimSubjectDTO2);
        reqOrClaimSubjectDTO2.setId("id2");
        assertThat(reqOrClaimSubjectDTO1).isNotEqualTo(reqOrClaimSubjectDTO2);
        reqOrClaimSubjectDTO1.setId(null);
        assertThat(reqOrClaimSubjectDTO1).isNotEqualTo(reqOrClaimSubjectDTO2);
    }
}
