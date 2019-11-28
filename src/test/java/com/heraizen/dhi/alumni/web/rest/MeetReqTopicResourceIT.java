package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.MeetReqTopic;
import com.heraizen.dhi.alumni.repository.MeetReqTopicRepository;
import com.heraizen.dhi.alumni.service.MeetReqTopicService;
import com.heraizen.dhi.alumni.service.dto.MeetReqTopicDTO;
import com.heraizen.dhi.alumni.service.mapper.MeetReqTopicMapper;
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
 * Integration tests for the {@link MeetReqTopicResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class MeetReqTopicResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MeetReqTopicRepository meetReqTopicRepository;

    @Autowired
    private MeetReqTopicMapper meetReqTopicMapper;

    @Autowired
    private MeetReqTopicService meetReqTopicService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restMeetReqTopicMockMvc;

    private MeetReqTopic meetReqTopic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MeetReqTopicResource meetReqTopicResource = new MeetReqTopicResource(meetReqTopicService);
        this.restMeetReqTopicMockMvc = MockMvcBuilders.standaloneSetup(meetReqTopicResource)
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
    public static MeetReqTopic createEntity() {
        MeetReqTopic meetReqTopic = new MeetReqTopic()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return meetReqTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetReqTopic createUpdatedEntity() {
        MeetReqTopic meetReqTopic = new MeetReqTopic()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return meetReqTopic;
    }

    @BeforeEach
    public void initTest() {
        meetReqTopicRepository.deleteAll();
        meetReqTopic = createEntity();
    }

    @Test
    public void createMeetReqTopic() throws Exception {
        int databaseSizeBeforeCreate = meetReqTopicRepository.findAll().size();

        // Create the MeetReqTopic
        MeetReqTopicDTO meetReqTopicDTO = meetReqTopicMapper.toDto(meetReqTopic);
        restMeetReqTopicMockMvc.perform(post("/api/meet-req-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(meetReqTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the MeetReqTopic in the database
        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeCreate + 1);
        MeetReqTopic testMeetReqTopic = meetReqTopicList.get(meetReqTopicList.size() - 1);
        assertThat(testMeetReqTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMeetReqTopic.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createMeetReqTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = meetReqTopicRepository.findAll().size();

        // Create the MeetReqTopic with an existing ID
        meetReqTopic.setId("existing_id");
        MeetReqTopicDTO meetReqTopicDTO = meetReqTopicMapper.toDto(meetReqTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetReqTopicMockMvc.perform(post("/api/meet-req-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(meetReqTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeetReqTopic in the database
        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetReqTopicRepository.findAll().size();
        // set the field null
        meetReqTopic.setName(null);

        // Create the MeetReqTopic, which fails.
        MeetReqTopicDTO meetReqTopicDTO = meetReqTopicMapper.toDto(meetReqTopic);

        restMeetReqTopicMockMvc.perform(post("/api/meet-req-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(meetReqTopicDTO)))
            .andExpect(status().isBadRequest());

        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMeetReqTopics() throws Exception {
        // Initialize the database
        meetReqTopicRepository.save(meetReqTopic);

        // Get all the meetReqTopicList
        restMeetReqTopicMockMvc.perform(get("/api/meet-req-topics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meetReqTopic.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    public void getMeetReqTopic() throws Exception {
        // Initialize the database
        meetReqTopicRepository.save(meetReqTopic);

        // Get the meetReqTopic
        restMeetReqTopicMockMvc.perform(get("/api/meet-req-topics/{id}", meetReqTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(meetReqTopic.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    public void getNonExistingMeetReqTopic() throws Exception {
        // Get the meetReqTopic
        restMeetReqTopicMockMvc.perform(get("/api/meet-req-topics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMeetReqTopic() throws Exception {
        // Initialize the database
        meetReqTopicRepository.save(meetReqTopic);

        int databaseSizeBeforeUpdate = meetReqTopicRepository.findAll().size();

        // Update the meetReqTopic
        MeetReqTopic updatedMeetReqTopic = meetReqTopicRepository.findById(meetReqTopic.getId()).get();
        updatedMeetReqTopic
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        MeetReqTopicDTO meetReqTopicDTO = meetReqTopicMapper.toDto(updatedMeetReqTopic);

        restMeetReqTopicMockMvc.perform(put("/api/meet-req-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(meetReqTopicDTO)))
            .andExpect(status().isOk());

        // Validate the MeetReqTopic in the database
        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeUpdate);
        MeetReqTopic testMeetReqTopic = meetReqTopicList.get(meetReqTopicList.size() - 1);
        assertThat(testMeetReqTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMeetReqTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingMeetReqTopic() throws Exception {
        int databaseSizeBeforeUpdate = meetReqTopicRepository.findAll().size();

        // Create the MeetReqTopic
        MeetReqTopicDTO meetReqTopicDTO = meetReqTopicMapper.toDto(meetReqTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetReqTopicMockMvc.perform(put("/api/meet-req-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(meetReqTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeetReqTopic in the database
        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMeetReqTopic() throws Exception {
        // Initialize the database
        meetReqTopicRepository.save(meetReqTopic);

        int databaseSizeBeforeDelete = meetReqTopicRepository.findAll().size();

        // Delete the meetReqTopic
        restMeetReqTopicMockMvc.perform(delete("/api/meet-req-topics/{id}", meetReqTopic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeetReqTopic> meetReqTopicList = meetReqTopicRepository.findAll();
        assertThat(meetReqTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
