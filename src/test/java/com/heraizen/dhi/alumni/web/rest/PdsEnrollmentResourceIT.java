package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.PdsEnrollment;
import com.heraizen.dhi.alumni.domain.User;
import com.heraizen.dhi.alumni.domain.PdsSchedule;
import com.heraizen.dhi.alumni.repository.PdsEnrollmentRepository;
import com.heraizen.dhi.alumni.service.PdsEnrollmentService;
import com.heraizen.dhi.alumni.service.dto.PdsEnrollmentDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsEnrollmentMapper;
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


import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PdsEnrollmentResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class PdsEnrollmentResourceIT {

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private PdsEnrollmentRepository pdsEnrollmentRepository;

    @Autowired
    private PdsEnrollmentMapper pdsEnrollmentMapper;

    @Autowired
    private PdsEnrollmentService pdsEnrollmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPdsEnrollmentMockMvc;

    private PdsEnrollment pdsEnrollment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PdsEnrollmentResource pdsEnrollmentResource = new PdsEnrollmentResource(pdsEnrollmentService);
        this.restPdsEnrollmentMockMvc = MockMvcBuilders.standaloneSetup(pdsEnrollmentResource)
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
    public static PdsEnrollment createEntity() {
        PdsEnrollment pdsEnrollment = new PdsEnrollment()
            .remarks(DEFAULT_REMARKS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        pdsEnrollment.setEnrolledBy(user);
        // Add required entity
        PdsSchedule pdsSchedule;
        pdsSchedule = PdsScheduleResourceIT.createEntity();
        pdsSchedule.setId("fixed-id-for-tests");
        pdsEnrollment.setEnrolledTo(pdsSchedule);
        return pdsEnrollment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdsEnrollment createUpdatedEntity() {
        PdsEnrollment pdsEnrollment = new PdsEnrollment()
            .remarks(UPDATED_REMARKS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        pdsEnrollment.setEnrolledBy(user);
        // Add required entity
        PdsSchedule pdsSchedule;
        pdsSchedule = PdsScheduleResourceIT.createUpdatedEntity();
        pdsSchedule.setId("fixed-id-for-tests");
        pdsEnrollment.setEnrolledTo(pdsSchedule);
        return pdsEnrollment;
    }

    @BeforeEach
    public void initTest() {
        pdsEnrollmentRepository.deleteAll();
        pdsEnrollment = createEntity();
    }

    @Test
    public void createPdsEnrollment() throws Exception {
        int databaseSizeBeforeCreate = pdsEnrollmentRepository.findAll().size();

        // Create the PdsEnrollment
        PdsEnrollmentDTO pdsEnrollmentDTO = pdsEnrollmentMapper.toDto(pdsEnrollment);
        restPdsEnrollmentMockMvc.perform(post("/api/pds-enrollments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsEnrollmentDTO)))
            .andExpect(status().isCreated());

        // Validate the PdsEnrollment in the database
        List<PdsEnrollment> pdsEnrollmentList = pdsEnrollmentRepository.findAll();
        assertThat(pdsEnrollmentList).hasSize(databaseSizeBeforeCreate + 1);
        PdsEnrollment testPdsEnrollment = pdsEnrollmentList.get(pdsEnrollmentList.size() - 1);
        assertThat(testPdsEnrollment.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    public void createPdsEnrollmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdsEnrollmentRepository.findAll().size();

        // Create the PdsEnrollment with an existing ID
        pdsEnrollment.setId("existing_id");
        PdsEnrollmentDTO pdsEnrollmentDTO = pdsEnrollmentMapper.toDto(pdsEnrollment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdsEnrollmentMockMvc.perform(post("/api/pds-enrollments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsEnrollmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsEnrollment in the database
        List<PdsEnrollment> pdsEnrollmentList = pdsEnrollmentRepository.findAll();
        assertThat(pdsEnrollmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllPdsEnrollments() throws Exception {
        // Initialize the database
        pdsEnrollmentRepository.save(pdsEnrollment);

        // Get all the pdsEnrollmentList
        restPdsEnrollmentMockMvc.perform(get("/api/pds-enrollments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdsEnrollment.getId())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }
    
    @Test
    public void getPdsEnrollment() throws Exception {
        // Initialize the database
        pdsEnrollmentRepository.save(pdsEnrollment);

        // Get the pdsEnrollment
        restPdsEnrollmentMockMvc.perform(get("/api/pds-enrollments/{id}", pdsEnrollment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pdsEnrollment.getId()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }

    @Test
    public void getNonExistingPdsEnrollment() throws Exception {
        // Get the pdsEnrollment
        restPdsEnrollmentMockMvc.perform(get("/api/pds-enrollments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePdsEnrollment() throws Exception {
        // Initialize the database
        pdsEnrollmentRepository.save(pdsEnrollment);

        int databaseSizeBeforeUpdate = pdsEnrollmentRepository.findAll().size();

        // Update the pdsEnrollment
        PdsEnrollment updatedPdsEnrollment = pdsEnrollmentRepository.findById(pdsEnrollment.getId()).get();
        updatedPdsEnrollment
            .remarks(UPDATED_REMARKS);
        PdsEnrollmentDTO pdsEnrollmentDTO = pdsEnrollmentMapper.toDto(updatedPdsEnrollment);

        restPdsEnrollmentMockMvc.perform(put("/api/pds-enrollments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsEnrollmentDTO)))
            .andExpect(status().isOk());

        // Validate the PdsEnrollment in the database
        List<PdsEnrollment> pdsEnrollmentList = pdsEnrollmentRepository.findAll();
        assertThat(pdsEnrollmentList).hasSize(databaseSizeBeforeUpdate);
        PdsEnrollment testPdsEnrollment = pdsEnrollmentList.get(pdsEnrollmentList.size() - 1);
        assertThat(testPdsEnrollment.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    public void updateNonExistingPdsEnrollment() throws Exception {
        int databaseSizeBeforeUpdate = pdsEnrollmentRepository.findAll().size();

        // Create the PdsEnrollment
        PdsEnrollmentDTO pdsEnrollmentDTO = pdsEnrollmentMapper.toDto(pdsEnrollment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdsEnrollmentMockMvc.perform(put("/api/pds-enrollments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsEnrollmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsEnrollment in the database
        List<PdsEnrollment> pdsEnrollmentList = pdsEnrollmentRepository.findAll();
        assertThat(pdsEnrollmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePdsEnrollment() throws Exception {
        // Initialize the database
        pdsEnrollmentRepository.save(pdsEnrollment);

        int databaseSizeBeforeDelete = pdsEnrollmentRepository.findAll().size();

        // Delete the pdsEnrollment
        restPdsEnrollmentMockMvc.perform(delete("/api/pds-enrollments/{id}", pdsEnrollment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdsEnrollment> pdsEnrollmentList = pdsEnrollmentRepository.findAll();
        assertThat(pdsEnrollmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
