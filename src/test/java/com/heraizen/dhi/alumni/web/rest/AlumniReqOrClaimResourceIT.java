package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.AlumniReqOrClaim;
import com.heraizen.dhi.alumni.domain.User;
import com.heraizen.dhi.alumni.domain.ReqOrClaimSubject;
import com.heraizen.dhi.alumni.repository.AlumniReqOrClaimRepository;
import com.heraizen.dhi.alumni.service.AlumniReqOrClaimService;
import com.heraizen.dhi.alumni.service.dto.AlumniReqOrClaimDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniReqOrClaimMapper;
import com.heraizen.dhi.alumni.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.heraizen.dhi.alumni.domain.enumeration.ReqOrClaimStatus;
/**
 * Integration tests for the {@link AlumniReqOrClaimResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class AlumniReqOrClaimResourceIT {

    private static final String DEFAULT_REQUEST_FOR = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_FOR = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final ReqOrClaimStatus DEFAULT_STATUS = ReqOrClaimStatus.SUBMITTED;
    private static final ReqOrClaimStatus UPDATED_STATUS = ReqOrClaimStatus.PROCESSING;

    private static final Instant DEFAULT_REQUESTED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REQUESTED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AlumniReqOrClaimRepository alumniReqOrClaimRepository;

    @Autowired
    private AlumniReqOrClaimMapper alumniReqOrClaimMapper;

    @Autowired
    private AlumniReqOrClaimService alumniReqOrClaimService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAlumniReqOrClaimMockMvc;

    private AlumniReqOrClaim alumniReqOrClaim;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlumniReqOrClaimResource alumniReqOrClaimResource = new AlumniReqOrClaimResource(alumniReqOrClaimService);
        this.restAlumniReqOrClaimMockMvc = MockMvcBuilders.standaloneSetup(alumniReqOrClaimResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumniReqOrClaim createEntity() {
        AlumniReqOrClaim alumniReqOrClaim = new AlumniReqOrClaim()
            .requestFor(DEFAULT_REQUEST_FOR)
            .reason(DEFAULT_REASON)
            .status(DEFAULT_STATUS)
            .requestedDate(DEFAULT_REQUESTED_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        alumniReqOrClaim.setRequestBy(user);
        // Add required entity
        alumniReqOrClaim.setAssignee(user);
        // Add required entity
        ReqOrClaimSubject reqOrClaimSubject;
        reqOrClaimSubject = ReqOrClaimSubjectResourceIT.createEntity();
        reqOrClaimSubject.setId("fixed-id-for-tests");
        alumniReqOrClaim.setSubject(reqOrClaimSubject);
        return alumniReqOrClaim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumniReqOrClaim createUpdatedEntity() {
        AlumniReqOrClaim alumniReqOrClaim = new AlumniReqOrClaim()
            .requestFor(UPDATED_REQUEST_FOR)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS)
            .requestedDate(UPDATED_REQUESTED_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        alumniReqOrClaim.setRequestBy(user);
        // Add required entity
        alumniReqOrClaim.setAssignee(user);
        // Add required entity
        ReqOrClaimSubject reqOrClaimSubject;
        reqOrClaimSubject = ReqOrClaimSubjectResourceIT.createUpdatedEntity();
        reqOrClaimSubject.setId("fixed-id-for-tests");
        alumniReqOrClaim.setSubject(reqOrClaimSubject);
        return alumniReqOrClaim;
    }

    @BeforeEach
    public void initTest() {
        alumniReqOrClaimRepository.deleteAll();
        alumniReqOrClaim = createEntity();
    }

    @Test
    public void createAlumniReqOrClaim() throws Exception {
        int databaseSizeBeforeCreate = alumniReqOrClaimRepository.findAll().size();

        // Create the AlumniReqOrClaim
        AlumniReqOrClaimDTO alumniReqOrClaimDTO = alumniReqOrClaimMapper.toDto(alumniReqOrClaim);
        restAlumniReqOrClaimMockMvc.perform(post("/api/alumni-req-or-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniReqOrClaimDTO)))
            .andExpect(status().isCreated());

        // Validate the AlumniReqOrClaim in the database
        List<AlumniReqOrClaim> alumniReqOrClaimList = alumniReqOrClaimRepository.findAll();
        assertThat(alumniReqOrClaimList).hasSize(databaseSizeBeforeCreate + 1);
        AlumniReqOrClaim testAlumniReqOrClaim = alumniReqOrClaimList.get(alumniReqOrClaimList.size() - 1);
        assertThat(testAlumniReqOrClaim.getRequestFor()).isEqualTo(DEFAULT_REQUEST_FOR);
        assertThat(testAlumniReqOrClaim.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testAlumniReqOrClaim.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAlumniReqOrClaim.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
    }

    @Test
    public void createAlumniReqOrClaimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniReqOrClaimRepository.findAll().size();

        // Create the AlumniReqOrClaim with an existing ID
        alumniReqOrClaim.setId("existing_id");
        AlumniReqOrClaimDTO alumniReqOrClaimDTO = alumniReqOrClaimMapper.toDto(alumniReqOrClaim);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniReqOrClaimMockMvc.perform(post("/api/alumni-req-or-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniReqOrClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlumniReqOrClaim in the database
        List<AlumniReqOrClaim> alumniReqOrClaimList = alumniReqOrClaimRepository.findAll();
        assertThat(alumniReqOrClaimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAlumniReqOrClaims() throws Exception {
        // Initialize the database
        alumniReqOrClaimRepository.save(alumniReqOrClaim);

        // Get all the alumniReqOrClaimList
        restAlumniReqOrClaimMockMvc.perform(get("/api/alumni-req-or-claims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniReqOrClaim.getId())))
            .andExpect(jsonPath("$.[*].requestFor").value(hasItem(DEFAULT_REQUEST_FOR)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE.toString())));
    }
    
    @Test
    public void getAlumniReqOrClaim() throws Exception {
        // Initialize the database
        alumniReqOrClaimRepository.save(alumniReqOrClaim);

        // Get the alumniReqOrClaim
        restAlumniReqOrClaimMockMvc.perform(get("/api/alumni-req-or-claims/{id}", alumniReqOrClaim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumniReqOrClaim.getId()))
            .andExpect(jsonPath("$.requestFor").value(DEFAULT_REQUEST_FOR))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE.toString()));
    }

    @Test
    public void getNonExistingAlumniReqOrClaim() throws Exception {
        // Get the alumniReqOrClaim
        restAlumniReqOrClaimMockMvc.perform(get("/api/alumni-req-or-claims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAlumniReqOrClaim() throws Exception {
        // Initialize the database
        alumniReqOrClaimRepository.save(alumniReqOrClaim);

        int databaseSizeBeforeUpdate = alumniReqOrClaimRepository.findAll().size();

        // Update the alumniReqOrClaim
        AlumniReqOrClaim updatedAlumniReqOrClaim = alumniReqOrClaimRepository.findById(alumniReqOrClaim.getId()).get();
        updatedAlumniReqOrClaim
            .requestFor(UPDATED_REQUEST_FOR)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS)
            .requestedDate(UPDATED_REQUESTED_DATE);
        AlumniReqOrClaimDTO alumniReqOrClaimDTO = alumniReqOrClaimMapper.toDto(updatedAlumniReqOrClaim);

        restAlumniReqOrClaimMockMvc.perform(put("/api/alumni-req-or-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniReqOrClaimDTO)))
            .andExpect(status().isOk());

        // Validate the AlumniReqOrClaim in the database
        List<AlumniReqOrClaim> alumniReqOrClaimList = alumniReqOrClaimRepository.findAll();
        assertThat(alumniReqOrClaimList).hasSize(databaseSizeBeforeUpdate);
        AlumniReqOrClaim testAlumniReqOrClaim = alumniReqOrClaimList.get(alumniReqOrClaimList.size() - 1);
        assertThat(testAlumniReqOrClaim.getRequestFor()).isEqualTo(UPDATED_REQUEST_FOR);
        assertThat(testAlumniReqOrClaim.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testAlumniReqOrClaim.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAlumniReqOrClaim.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
    }

    @Test
    public void updateNonExistingAlumniReqOrClaim() throws Exception {
        int databaseSizeBeforeUpdate = alumniReqOrClaimRepository.findAll().size();

        // Create the AlumniReqOrClaim
        AlumniReqOrClaimDTO alumniReqOrClaimDTO = alumniReqOrClaimMapper.toDto(alumniReqOrClaim);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumniReqOrClaimMockMvc.perform(put("/api/alumni-req-or-claims")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniReqOrClaimDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlumniReqOrClaim in the database
        List<AlumniReqOrClaim> alumniReqOrClaimList = alumniReqOrClaimRepository.findAll();
        assertThat(alumniReqOrClaimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAlumniReqOrClaim() throws Exception {
        // Initialize the database
        alumniReqOrClaimRepository.save(alumniReqOrClaim);

        int databaseSizeBeforeDelete = alumniReqOrClaimRepository.findAll().size();

        // Delete the alumniReqOrClaim
        restAlumniReqOrClaimMockMvc.perform(delete("/api/alumni-req-or-claims/{id}", alumniReqOrClaim.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlumniReqOrClaim> alumniReqOrClaimList = alumniReqOrClaimRepository.findAll();
        assertThat(alumniReqOrClaimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
