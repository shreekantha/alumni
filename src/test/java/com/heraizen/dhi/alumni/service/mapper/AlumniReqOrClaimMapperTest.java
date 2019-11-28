package com.heraizen.dhi.alumni.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AlumniReqOrClaimMapperTest {

    private AlumniReqOrClaimMapper alumniReqOrClaimMapper;

    @BeforeEach
    public void setUp() {
        alumniReqOrClaimMapper = new AlumniReqOrClaimMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
        assertThat(alumniReqOrClaimMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(alumniReqOrClaimMapper.fromId(null)).isNull();
    }
}
