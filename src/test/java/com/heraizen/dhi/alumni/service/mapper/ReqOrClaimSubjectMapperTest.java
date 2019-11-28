package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ReqOrClaimSubjectMapperTest {

    private ReqOrClaimSubjectMapper reqOrClaimSubjectMapper;

    @BeforeEach
    public void setUp() {
        reqOrClaimSubjectMapper = new ReqOrClaimSubjectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(reqOrClaimSubjectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reqOrClaimSubjectMapper.fromId(null)).isNull();
    }
}
