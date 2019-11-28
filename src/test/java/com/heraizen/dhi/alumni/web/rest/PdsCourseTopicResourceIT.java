package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.PdsCourseTopic;
import com.heraizen.dhi.alumni.domain.PdsCourse;
import com.heraizen.dhi.alumni.repository.PdsCourseTopicRepository;
import com.heraizen.dhi.alumni.service.PdsCourseTopicService;
import com.heraizen.dhi.alumni.service.dto.PdsCourseTopicDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsCourseTopicMapper;
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


import java.time.Duration;
import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PdsCourseTopicResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class PdsCourseTopicResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Duration DEFAULT_DURATION = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION = Duration.ofHours(12);

    @Autowired
    private PdsCourseTopicRepository pdsCourseTopicRepository;

    @Autowired
    private PdsCourseTopicMapper pdsCourseTopicMapper;

    @Autowired
    private PdsCourseTopicService pdsCourseTopicService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPdsCourseTopicMockMvc;

    private PdsCourseTopic pdsCourseTopic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PdsCourseTopicResource pdsCourseTopicResource = new PdsCourseTopicResource(pdsCourseTopicService);
        this.restPdsCourseTopicMockMvc = MockMvcBuilders.standaloneSetup(pdsCourseTopicResource)
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
    public static PdsCourseTopic createEntity() {
        PdsCourseTopic pdsCourseTopic = new PdsCourseTopic()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .duration(DEFAULT_DURATION);
        // Add required entity
        PdsCourse pdsCourse;
        pdsCourse = PdsCourseResourceIT.createEntity();
        pdsCourse.setId("fixed-id-for-tests");
        pdsCourseTopic.setPdsCourse(pdsCourse);
        return pdsCourseTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdsCourseTopic createUpdatedEntity() {
        PdsCourseTopic pdsCourseTopic = new PdsCourseTopic()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .duration(UPDATED_DURATION);
        // Add required entity
        PdsCourse pdsCourse;
        pdsCourse = PdsCourseResourceIT.createUpdatedEntity();
        pdsCourse.setId("fixed-id-for-tests");
        pdsCourseTopic.setPdsCourse(pdsCourse);
        return pdsCourseTopic;
    }

    @BeforeEach
    public void initTest() {
        pdsCourseTopicRepository.deleteAll();
        pdsCourseTopic = createEntity();
    }

    @Test
    public void createPdsCourseTopic() throws Exception {
        int databaseSizeBeforeCreate = pdsCourseTopicRepository.findAll().size();

        // Create the PdsCourseTopic
        PdsCourseTopicDTO pdsCourseTopicDTO = pdsCourseTopicMapper.toDto(pdsCourseTopic);
        restPdsCourseTopicMockMvc.perform(post("/api/pds-course-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the PdsCourseTopic in the database
        List<PdsCourseTopic> pdsCourseTopicList = pdsCourseTopicRepository.findAll();
        assertThat(pdsCourseTopicList).hasSize(databaseSizeBeforeCreate + 1);
        PdsCourseTopic testPdsCourseTopic = pdsCourseTopicList.get(pdsCourseTopicList.size() - 1);
        assertThat(testPdsCourseTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPdsCourseTopic.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPdsCourseTopic.getDuration()).isEqualTo(DEFAULT_DURATION);
    }

    @Test
    public void createPdsCourseTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdsCourseTopicRepository.findAll().size();

        // Create the PdsCourseTopic with an existing ID
        pdsCourseTopic.setId("existing_id");
        PdsCourseTopicDTO pdsCourseTopicDTO = pdsCourseTopicMapper.toDto(pdsCourseTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdsCourseTopicMockMvc.perform(post("/api/pds-course-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsCourseTopic in the database
        List<PdsCourseTopic> pdsCourseTopicList = pdsCourseTopicRepository.findAll();
        assertThat(pdsCourseTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllPdsCourseTopics() throws Exception {
        // Initialize the database
        pdsCourseTopicRepository.save(pdsCourseTopic);

        // Get all the pdsCourseTopicList
        restPdsCourseTopicMockMvc.perform(get("/api/pds-course-topics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdsCourseTopic.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())));
    }
    
    @Test
    public void getPdsCourseTopic() throws Exception {
        // Initialize the database
        pdsCourseTopicRepository.save(pdsCourseTopic);

        // Get the pdsCourseTopic
        restPdsCourseTopicMockMvc.perform(get("/api/pds-course-topics/{id}", pdsCourseTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pdsCourseTopic.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()));
    }

    @Test
    public void getNonExistingPdsCourseTopic() throws Exception {
        // Get the pdsCourseTopic
        restPdsCourseTopicMockMvc.perform(get("/api/pds-course-topics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePdsCourseTopic() throws Exception {
        // Initialize the database
        pdsCourseTopicRepository.save(pdsCourseTopic);

        int databaseSizeBeforeUpdate = pdsCourseTopicRepository.findAll().size();

        // Update the pdsCourseTopic
        PdsCourseTopic updatedPdsCourseTopic = pdsCourseTopicRepository.findById(pdsCourseTopic.getId()).get();
        updatedPdsCourseTopic
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .duration(UPDATED_DURATION);
        PdsCourseTopicDTO pdsCourseTopicDTO = pdsCourseTopicMapper.toDto(updatedPdsCourseTopic);

        restPdsCourseTopicMockMvc.perform(put("/api/pds-course-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseTopicDTO)))
            .andExpect(status().isOk());

        // Validate the PdsCourseTopic in the database
        List<PdsCourseTopic> pdsCourseTopicList = pdsCourseTopicRepository.findAll();
        assertThat(pdsCourseTopicList).hasSize(databaseSizeBeforeUpdate);
        PdsCourseTopic testPdsCourseTopic = pdsCourseTopicList.get(pdsCourseTopicList.size() - 1);
        assertThat(testPdsCourseTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPdsCourseTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPdsCourseTopic.getDuration()).isEqualTo(UPDATED_DURATION);
    }

    @Test
    public void updateNonExistingPdsCourseTopic() throws Exception {
        int databaseSizeBeforeUpdate = pdsCourseTopicRepository.findAll().size();

        // Create the PdsCourseTopic
        PdsCourseTopicDTO pdsCourseTopicDTO = pdsCourseTopicMapper.toDto(pdsCourseTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdsCourseTopicMockMvc.perform(put("/api/pds-course-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsCourseTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsCourseTopic in the database
        List<PdsCourseTopic> pdsCourseTopicList = pdsCourseTopicRepository.findAll();
        assertThat(pdsCourseTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePdsCourseTopic() throws Exception {
        // Initialize the database
        pdsCourseTopicRepository.save(pdsCourseTopic);

        int databaseSizeBeforeDelete = pdsCourseTopicRepository.findAll().size();

        // Delete the pdsCourseTopic
        restPdsCourseTopicMockMvc.perform(delete("/api/pds-course-topics/{id}", pdsCourseTopic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdsCourseTopic> pdsCourseTopicList = pdsCourseTopicRepository.findAll();
        assertThat(pdsCourseTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
