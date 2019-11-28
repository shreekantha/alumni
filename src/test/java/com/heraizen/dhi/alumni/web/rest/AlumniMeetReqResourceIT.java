package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.AlumniMeetReq;
import com.heraizen.dhi.alumni.domain.User;
import com.heraizen.dhi.alumni.domain.MeetReqTopic;
import com.heraizen.dhi.alumni.domain.AspiredRole;
import com.heraizen.dhi.alumni.repository.AlumniMeetReqRepository;
import com.heraizen.dhi.alumni.service.AlumniMeetReqService;
import com.heraizen.dhi.alumni.service.dto.AlumniMeetReqDTO;
import com.heraizen.dhi.alumni.service.mapper.AlumniMeetReqMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;


import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.heraizen.dhi.alumni.domain.enumeration.AlumniMeetReqStatus;
/**
 * Integration tests for the {@link AlumniMeetReqResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class AlumniMeetReqResourceIT {

    private static final String DEFAULT_ABOUT_ME = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT_ME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOCUMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final AlumniMeetReqStatus DEFAULT_STATUS = AlumniMeetReqStatus.REQUESTED;
    private static final AlumniMeetReqStatus UPDATED_STATUS = AlumniMeetReqStatus.ACCEPTED;

    @Autowired
    private AlumniMeetReqRepository alumniMeetReqRepository;

    @Autowired
    private AlumniMeetReqMapper alumniMeetReqMapper;

    @Autowired
    private AlumniMeetReqService alumniMeetReqService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAlumniMeetReqMockMvc;

    private AlumniMeetReq alumniMeetReq;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlumniMeetReqResource alumniMeetReqResource = new AlumniMeetReqResource(alumniMeetReqService);
        this.restAlumniMeetReqMockMvc = MockMvcBuilders.standaloneSetup(alumniMeetReqResource)
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
    public static AlumniMeetReq createEntity() {
        AlumniMeetReq alumniMeetReq = new AlumniMeetReq()
            .aboutMe(DEFAULT_ABOUT_ME)
            .description(DEFAULT_DESCRIPTION)
            .document(DEFAULT_DOCUMENT)
            .documentContentType(DEFAULT_DOCUMENT_CONTENT_TYPE)
            .status(DEFAULT_STATUS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        alumniMeetReq.setRequestBy(user);
        // Add required entity
        alumniMeetReq.setRequestTo(user);
        // Add required entity
        MeetReqTopic meetReqTopic;
        meetReqTopic = MeetReqTopicResourceIT.createEntity();
        meetReqTopic.setId("fixed-id-for-tests");
        alumniMeetReq.setTopic(meetReqTopic);
        // Add required entity
        AspiredRole aspiredRole;
        aspiredRole = AspiredRoleResourceIT.createEntity();
        aspiredRole.setId("fixed-id-for-tests");
        alumniMeetReq.setAspiredRole(aspiredRole);
        return alumniMeetReq;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumniMeetReq createUpdatedEntity() {
        AlumniMeetReq alumniMeetReq = new AlumniMeetReq()
            .aboutMe(UPDATED_ABOUT_ME)
            .description(UPDATED_DESCRIPTION)
            .document(UPDATED_DOCUMENT)
            .documentContentType(UPDATED_DOCUMENT_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        alumniMeetReq.setRequestBy(user);
        // Add required entity
        alumniMeetReq.setRequestTo(user);
        // Add required entity
        MeetReqTopic meetReqTopic;
        meetReqTopic = MeetReqTopicResourceIT.createUpdatedEntity();
        meetReqTopic.setId("fixed-id-for-tests");
        alumniMeetReq.setTopic(meetReqTopic);
        // Add required entity
        AspiredRole aspiredRole;
        aspiredRole = AspiredRoleResourceIT.createUpdatedEntity();
        aspiredRole.setId("fixed-id-for-tests");
        alumniMeetReq.setAspiredRole(aspiredRole);
        return alumniMeetReq;
    }

    @BeforeEach
    public void initTest() {
        alumniMeetReqRepository.deleteAll();
        alumniMeetReq = createEntity();
    }

    @Test
    public void createAlumniMeetReq() throws Exception {
        int databaseSizeBeforeCreate = alumniMeetReqRepository.findAll().size();

        // Create the AlumniMeetReq
        AlumniMeetReqDTO alumniMeetReqDTO = alumniMeetReqMapper.toDto(alumniMeetReq);
        restAlumniMeetReqMockMvc.perform(post("/api/alumni-meet-reqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniMeetReqDTO)))
            .andExpect(status().isCreated());

        // Validate the AlumniMeetReq in the database
        List<AlumniMeetReq> alumniMeetReqList = alumniMeetReqRepository.findAll();
        assertThat(alumniMeetReqList).hasSize(databaseSizeBeforeCreate + 1);
        AlumniMeetReq testAlumniMeetReq = alumniMeetReqList.get(alumniMeetReqList.size() - 1);
        assertThat(testAlumniMeetReq.getAboutMe()).isEqualTo(DEFAULT_ABOUT_ME);
        assertThat(testAlumniMeetReq.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAlumniMeetReq.getDocument()).isEqualTo(DEFAULT_DOCUMENT);
        assertThat(testAlumniMeetReq.getDocumentContentType()).isEqualTo(DEFAULT_DOCUMENT_CONTENT_TYPE);
        assertThat(testAlumniMeetReq.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createAlumniMeetReqWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniMeetReqRepository.findAll().size();

        // Create the AlumniMeetReq with an existing ID
        alumniMeetReq.setId("existing_id");
        AlumniMeetReqDTO alumniMeetReqDTO = alumniMeetReqMapper.toDto(alumniMeetReq);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniMeetReqMockMvc.perform(post("/api/alumni-meet-reqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniMeetReqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlumniMeetReq in the database
        List<AlumniMeetReq> alumniMeetReqList = alumniMeetReqRepository.findAll();
        assertThat(alumniMeetReqList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAlumniMeetReqs() throws Exception {
        // Initialize the database
        alumniMeetReqRepository.save(alumniMeetReq);

        // Get all the alumniMeetReqList
        restAlumniMeetReqMockMvc.perform(get("/api/alumni-meet-reqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniMeetReq.getId())))
            .andExpect(jsonPath("$.[*].aboutMe").value(hasItem(DEFAULT_ABOUT_ME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].documentContentType").value(hasItem(DEFAULT_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].document").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENT))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getAlumniMeetReq() throws Exception {
        // Initialize the database
        alumniMeetReqRepository.save(alumniMeetReq);

        // Get the alumniMeetReq
        restAlumniMeetReqMockMvc.perform(get("/api/alumni-meet-reqs/{id}", alumniMeetReq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumniMeetReq.getId()))
            .andExpect(jsonPath("$.aboutMe").value(DEFAULT_ABOUT_ME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.documentContentType").value(DEFAULT_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.document").value(Base64Utils.encodeToString(DEFAULT_DOCUMENT)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingAlumniMeetReq() throws Exception {
        // Get the alumniMeetReq
        restAlumniMeetReqMockMvc.perform(get("/api/alumni-meet-reqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAlumniMeetReq() throws Exception {
        // Initialize the database
        alumniMeetReqRepository.save(alumniMeetReq);

        int databaseSizeBeforeUpdate = alumniMeetReqRepository.findAll().size();

        // Update the alumniMeetReq
        AlumniMeetReq updatedAlumniMeetReq = alumniMeetReqRepository.findById(alumniMeetReq.getId()).get();
        updatedAlumniMeetReq
            .aboutMe(UPDATED_ABOUT_ME)
            .description(UPDATED_DESCRIPTION)
            .document(UPDATED_DOCUMENT)
            .documentContentType(UPDATED_DOCUMENT_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        AlumniMeetReqDTO alumniMeetReqDTO = alumniMeetReqMapper.toDto(updatedAlumniMeetReq);

        restAlumniMeetReqMockMvc.perform(put("/api/alumni-meet-reqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniMeetReqDTO)))
            .andExpect(status().isOk());

        // Validate the AlumniMeetReq in the database
        List<AlumniMeetReq> alumniMeetReqList = alumniMeetReqRepository.findAll();
        assertThat(alumniMeetReqList).hasSize(databaseSizeBeforeUpdate);
        AlumniMeetReq testAlumniMeetReq = alumniMeetReqList.get(alumniMeetReqList.size() - 1);
        assertThat(testAlumniMeetReq.getAboutMe()).isEqualTo(UPDATED_ABOUT_ME);
        assertThat(testAlumniMeetReq.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAlumniMeetReq.getDocument()).isEqualTo(UPDATED_DOCUMENT);
        assertThat(testAlumniMeetReq.getDocumentContentType()).isEqualTo(UPDATED_DOCUMENT_CONTENT_TYPE);
        assertThat(testAlumniMeetReq.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingAlumniMeetReq() throws Exception {
        int databaseSizeBeforeUpdate = alumniMeetReqRepository.findAll().size();

        // Create the AlumniMeetReq
        AlumniMeetReqDTO alumniMeetReqDTO = alumniMeetReqMapper.toDto(alumniMeetReq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumniMeetReqMockMvc.perform(put("/api/alumni-meet-reqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniMeetReqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlumniMeetReq in the database
        List<AlumniMeetReq> alumniMeetReqList = alumniMeetReqRepository.findAll();
        assertThat(alumniMeetReqList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAlumniMeetReq() throws Exception {
        // Initialize the database
        alumniMeetReqRepository.save(alumniMeetReq);

        int databaseSizeBeforeDelete = alumniMeetReqRepository.findAll().size();

        // Delete the alumniMeetReq
        restAlumniMeetReqMockMvc.perform(delete("/api/alumni-meet-reqs/{id}", alumniMeetReq.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlumniMeetReq> alumniMeetReqList = alumniMeetReqRepository.findAll();
        assertThat(alumniMeetReqList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
