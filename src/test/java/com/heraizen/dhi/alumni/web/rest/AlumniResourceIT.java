package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.Alumni;
import com.heraizen.dhi.alumni.repository.AlumniRepository;
import com.heraizen.dhi.alumni.service.AlumniService;
import com.heraizen.dhi.alumni.service.dto.AlumniDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniMapper;
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
 * Integration tests for the {@link AlumniResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class AlumniResourceIT {

    private static final String DEFAULT_USN = "AAAAAAAAAA";
    private static final String UPDATED_USN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_DEGREE = "AAAAAAAAAA";
    private static final String UPDATED_DEGREE = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALIZATION = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALIZATION = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_JOB_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR_OF_GRADUATION = "AAAAAAAAAA";
    private static final String UPDATED_YEAR_OF_GRADUATION = "BBBBBBBBBB";

    private static final Double DEFAULT_EXPERIENCE = 1D;
    private static final Double UPDATED_EXPERIENCE = 2D;

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private AlumniMapper alumniMapper;

    @Autowired
    private AlumniService alumniService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAlumniMockMvc;

    private Alumni alumni;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlumniResource alumniResource = new AlumniResource(alumniService);
        this.restAlumniMockMvc = MockMvcBuilders.standaloneSetup(alumniResource)
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
    public static Alumni createEntity() {
        Alumni alumni = new Alumni()
            .usn(DEFAULT_USN)
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .degree(DEFAULT_DEGREE)
            .specialization(DEFAULT_SPECIALIZATION)
            .jobPosition(DEFAULT_JOB_POSITION)
            .company(DEFAULT_COMPANY)
            .yearOfGraduation(DEFAULT_YEAR_OF_GRADUATION)
            .experience(DEFAULT_EXPERIENCE);
        return alumni;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alumni createUpdatedEntity() {
        Alumni alumni = new Alumni()
            .usn(UPDATED_USN)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .degree(UPDATED_DEGREE)
            .specialization(UPDATED_SPECIALIZATION)
            .jobPosition(UPDATED_JOB_POSITION)
            .company(UPDATED_COMPANY)
            .yearOfGraduation(UPDATED_YEAR_OF_GRADUATION)
            .experience(UPDATED_EXPERIENCE);
        return alumni;
    }

    @BeforeEach
    public void initTest() {
        alumniRepository.deleteAll();
        alumni = createEntity();
    }

    @Test
    public void createAlumni() throws Exception {
        int databaseSizeBeforeCreate = alumniRepository.findAll().size();

        // Create the Alumni
        AlumniDTO alumniDTO = alumniMapper.toDto(alumni);
        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniDTO)))
            .andExpect(status().isCreated());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeCreate + 1);
        Alumni testAlumni = alumniList.get(alumniList.size() - 1);
        assertThat(testAlumni.getUsn()).isEqualTo(DEFAULT_USN);
        assertThat(testAlumni.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAlumni.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAlumni.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testAlumni.getDegree()).isEqualTo(DEFAULT_DEGREE);
        assertThat(testAlumni.getSpecialization()).isEqualTo(DEFAULT_SPECIALIZATION);
        assertThat(testAlumni.getJobPosition()).isEqualTo(DEFAULT_JOB_POSITION);
        assertThat(testAlumni.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testAlumni.getYearOfGraduation()).isEqualTo(DEFAULT_YEAR_OF_GRADUATION);
        assertThat(testAlumni.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
    }

    @Test
    public void createAlumniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniRepository.findAll().size();

        // Create the Alumni with an existing ID
        alumni.setId("existing_id");
        AlumniDTO alumniDTO = alumniMapper.toDto(alumni);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAlumni() throws Exception {
        // Initialize the database
        alumniRepository.save(alumni);

        // Get all the alumniList
        restAlumniMockMvc.perform(get("/api/alumni?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumni.getId())))
            .andExpect(jsonPath("$.[*].usn").value(hasItem(DEFAULT_USN)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].degree").value(hasItem(DEFAULT_DEGREE)))
            .andExpect(jsonPath("$.[*].specialization").value(hasItem(DEFAULT_SPECIALIZATION)))
            .andExpect(jsonPath("$.[*].jobPosition").value(hasItem(DEFAULT_JOB_POSITION)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].yearOfGraduation").value(hasItem(DEFAULT_YEAR_OF_GRADUATION)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())));
    }
    
    @Test
    public void getAlumni() throws Exception {
        // Initialize the database
        alumniRepository.save(alumni);

        // Get the alumni
        restAlumniMockMvc.perform(get("/api/alumni/{id}", alumni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumni.getId()))
            .andExpect(jsonPath("$.usn").value(DEFAULT_USN))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.degree").value(DEFAULT_DEGREE))
            .andExpect(jsonPath("$.specialization").value(DEFAULT_SPECIALIZATION))
            .andExpect(jsonPath("$.jobPosition").value(DEFAULT_JOB_POSITION))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.yearOfGraduation").value(DEFAULT_YEAR_OF_GRADUATION))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.doubleValue()));
    }

    @Test
    public void getNonExistingAlumni() throws Exception {
        // Get the alumni
        restAlumniMockMvc.perform(get("/api/alumni/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAlumni() throws Exception {
        // Initialize the database
        alumniRepository.save(alumni);

        int databaseSizeBeforeUpdate = alumniRepository.findAll().size();

        // Update the alumni
        Alumni updatedAlumni = alumniRepository.findById(alumni.getId()).get();
        updatedAlumni
            .usn(UPDATED_USN)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .degree(UPDATED_DEGREE)
            .specialization(UPDATED_SPECIALIZATION)
            .jobPosition(UPDATED_JOB_POSITION)
            .company(UPDATED_COMPANY)
            .yearOfGraduation(UPDATED_YEAR_OF_GRADUATION)
            .experience(UPDATED_EXPERIENCE);
        AlumniDTO alumniDTO = alumniMapper.toDto(updatedAlumni);

        restAlumniMockMvc.perform(put("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniDTO)))
            .andExpect(status().isOk());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeUpdate);
        Alumni testAlumni = alumniList.get(alumniList.size() - 1);
        assertThat(testAlumni.getUsn()).isEqualTo(UPDATED_USN);
        assertThat(testAlumni.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAlumni.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAlumni.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testAlumni.getDegree()).isEqualTo(UPDATED_DEGREE);
        assertThat(testAlumni.getSpecialization()).isEqualTo(UPDATED_SPECIALIZATION);
        assertThat(testAlumni.getJobPosition()).isEqualTo(UPDATED_JOB_POSITION);
        assertThat(testAlumni.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testAlumni.getYearOfGraduation()).isEqualTo(UPDATED_YEAR_OF_GRADUATION);
        assertThat(testAlumni.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
    }

    @Test
    public void updateNonExistingAlumni() throws Exception {
        int databaseSizeBeforeUpdate = alumniRepository.findAll().size();

        // Create the Alumni
        AlumniDTO alumniDTO = alumniMapper.toDto(alumni);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumniMockMvc.perform(put("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAlumni() throws Exception {
        // Initialize the database
        alumniRepository.save(alumni);

        int databaseSizeBeforeDelete = alumniRepository.findAll().size();

        // Delete the alumni
        restAlumniMockMvc.perform(delete("/api/alumni/{id}", alumni.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
