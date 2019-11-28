package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.PdsCourse;
import com.heraizen.dhi.alumni.domain.ProfDevService;
import com.heraizen.dhi.alumni.repository.PdsCourseRepository;
import com.heraizen.dhi.alumni.service.PdsCourseService;
import com.heraizen.dhi.alumni.service.dto.PdsCourseDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsCourseMapper;
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
 * Integration tests for the {@link PdsCourseResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class PdsCourseResourceIT {

    private static final String DEFAULT_COURSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private PdsCourseRepository pdsCourseRepository;

    @Autowired
    private PdsCourseMapper pdsCourseMapper;

    @Autowired
    private PdsCourseService pdsCourseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPdsCourseMockMvc;

    private PdsCourse pdsCourse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PdsCourseResource pdsCourseResource = new PdsCourseResource(pdsCourseService);
        this.restPdsCourseMockMvc = MockMvcBuilders.standaloneSetup(pdsCourseResource)
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
    public static PdsCourse createEntity() {
        PdsCourse pdsCourse = new PdsCourse()
            .courseName(DEFAULT_COURSE_NAME)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        ProfDevService profDevService;
        profDevService = ProfDevServiceResourceIT.createEntity();
        profDevService.setId("fixed-id-for-tests");
        pdsCourse.setProfDevService(profDevService);
        return pdsCourse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdsCourse createUpdatedEntity() {
        PdsCourse pdsCourse = new PdsCourse()
            .courseName(UPDATED_COURSE_NAME)
            .description(UPDATED_DESCRIPTION);
        // Add required entity
        ProfDevService profDevService;
        profDevService = ProfDevServiceResourceIT.createUpdatedEntity();
        profDevService.setId("fixed-id-for-tests");
        pdsCourse.setProfDevService(profDevService);
        return pdsCourse;
    }

    @BeforeEach
    public void initTest() {
        pdsCourseRepository.deleteAll();
        pdsCourse = createEntity();
    }

    @Test
    public void createPdsCourse() throws Exception {
        int databaseSizeBeforeCreate = pdsCourseRepository.findAll().size();

        // Create the PdsCourse
        PdsCourseDTO pdsCourseDTO = pdsCourseMapper.toDto(pdsCourse);
        restPdsCourseMockMvc.perform(post("/api/pds-courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseDTO)))
            .andExpect(status().isCreated());

        // Validate the PdsCourse in the database
        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeCreate + 1);
        PdsCourse testPdsCourse = pdsCourseList.get(pdsCourseList.size() - 1);
        assertThat(testPdsCourse.getCourseName()).isEqualTo(DEFAULT_COURSE_NAME);
        assertThat(testPdsCourse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createPdsCourseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdsCourseRepository.findAll().size();

        // Create the PdsCourse with an existing ID
        pdsCourse.setId("existing_id");
        PdsCourseDTO pdsCourseDTO = pdsCourseMapper.toDto(pdsCourse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdsCourseMockMvc.perform(post("/api/pds-courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsCourse in the database
        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCourseNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdsCourseRepository.findAll().size();
        // set the field null
        pdsCourse.setCourseName(null);

        // Create the PdsCourse, which fails.
        PdsCourseDTO pdsCourseDTO = pdsCourseMapper.toDto(pdsCourse);

        restPdsCourseMockMvc.perform(post("/api/pds-courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseDTO)))
            .andExpect(status().isBadRequest());

        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPdsCourses() throws Exception {
        // Initialize the database
        pdsCourseRepository.save(pdsCourse);

        // Get all the pdsCourseList
        restPdsCourseMockMvc.perform(get("/api/pds-courses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdsCourse.getId())))
            .andExpect(jsonPath("$.[*].courseName").value(hasItem(DEFAULT_COURSE_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    public void getPdsCourse() throws Exception {
        // Initialize the database
        pdsCourseRepository.save(pdsCourse);

        // Get the pdsCourse
        restPdsCourseMockMvc.perform(get("/api/pds-courses/{id}", pdsCourse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pdsCourse.getId()))
            .andExpect(jsonPath("$.courseName").value(DEFAULT_COURSE_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    public void getNonExistingPdsCourse() throws Exception {
        // Get the pdsCourse
        restPdsCourseMockMvc.perform(get("/api/pds-courses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePdsCourse() throws Exception {
        // Initialize the database
        pdsCourseRepository.save(pdsCourse);

        int databaseSizeBeforeUpdate = pdsCourseRepository.findAll().size();

        // Update the pdsCourse
        PdsCourse updatedPdsCourse = pdsCourseRepository.findById(pdsCourse.getId()).get();
        updatedPdsCourse
            .courseName(UPDATED_COURSE_NAME)
            .description(UPDATED_DESCRIPTION);
        PdsCourseDTO pdsCourseDTO = pdsCourseMapper.toDto(updatedPdsCourse);

        restPdsCourseMockMvc.perform(put("/api/pds-courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseDTO)))
            .andExpect(status().isOk());

        // Validate the PdsCourse in the database
        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeUpdate);
        PdsCourse testPdsCourse = pdsCourseList.get(pdsCourseList.size() - 1);
        assertThat(testPdsCourse.getCourseName()).isEqualTo(UPDATED_COURSE_NAME);
        assertThat(testPdsCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingPdsCourse() throws Exception {
        int databaseSizeBeforeUpdate = pdsCourseRepository.findAll().size();

        // Create the PdsCourse
        PdsCourseDTO pdsCourseDTO = pdsCourseMapper.toDto(pdsCourse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdsCourseMockMvc.perform(put("/api/pds-courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsCourse in the database
        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePdsCourse() throws Exception {
        // Initialize the database
        pdsCourseRepository.save(pdsCourse);

        int databaseSizeBeforeDelete = pdsCourseRepository.findAll().size();

        // Delete the pdsCourse
        restPdsCourseMockMvc.perform(delete("/api/pds-courses/{id}", pdsCourse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdsCourse> pdsCourseList = pdsCourseRepository.findAll();
        assertThat(pdsCourseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
