package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.ReqOrClaimSubject;
import com.heraizen.dhi.alumni.repository.ReqOrClaimSubjectRepository;
import com.heraizen.dhi.alumni.service.ReqOrClaimSubjectService;
import com.heraizen.dhi.alumni.service.dto.ReqOrClaimSubjectDTO;
import com.heraizen.dhi.alumni.service.mapper.ReqOrClaimSubjectMapper;
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
 * Integration tests for the {@link ReqOrClaimSubjectResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class ReqOrClaimSubjectResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DECSRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DECSRIPTION = "BBBBBBBBBB";

    @Autowired
    private ReqOrClaimSubjectRepository reqOrClaimSubjectRepository;

    @Autowired
    private ReqOrClaimSubjectMapper reqOrClaimSubjectMapper;

    @Autowired
    private ReqOrClaimSubjectService reqOrClaimSubjectService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restReqOrClaimSubjectMockMvc;

    private ReqOrClaimSubject reqOrClaimSubject;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReqOrClaimSubjectResource reqOrClaimSubjectResource = new ReqOrClaimSubjectResource(reqOrClaimSubjectService);
        this.restReqOrClaimSubjectMockMvc = MockMvcBuilders.standaloneSetup(reqOrClaimSubjectResource)
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
    public static ReqOrClaimSubject createEntity() {
        ReqOrClaimSubject reqOrClaimSubject = new ReqOrClaimSubject()
            .name(DEFAULT_NAME)
            .decsription(DEFAULT_DECSRIPTION);
        return reqOrClaimSubject;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReqOrClaimSubject createUpdatedEntity() {
        ReqOrClaimSubject reqOrClaimSubject = new ReqOrClaimSubject()
            .name(UPDATED_NAME)
            .decsription(UPDATED_DECSRIPTION);
        return reqOrClaimSubject;
    }

    @BeforeEach
    public void initTest() {
        reqOrClaimSubjectRepository.deleteAll();
        reqOrClaimSubject = createEntity();
    }

    @Test
    public void createReqOrClaimSubject() throws Exception {
        int databaseSizeBeforeCreate = reqOrClaimSubjectRepository.findAll().size();

        // Create the ReqOrClaimSubject
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO = reqOrClaimSubjectMapper.toDto(reqOrClaimSubject);
        restReqOrClaimSubjectMockMvc.perform(post("/api/req-or-claim-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reqOrClaimSubjectDTO)))
            .andExpect(status().isCreated());

        // Validate the ReqOrClaimSubject in the database
        List<ReqOrClaimSubject> reqOrClaimSubjectList = reqOrClaimSubjectRepository.findAll();
        assertThat(reqOrClaimSubjectList).hasSize(databaseSizeBeforeCreate + 1);
        ReqOrClaimSubject testReqOrClaimSubject = reqOrClaimSubjectList.get(reqOrClaimSubjectList.size() - 1);
        assertThat(testReqOrClaimSubject.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testReqOrClaimSubject.getDecsription()).isEqualTo(DEFAULT_DECSRIPTION);
    }

    @Test
    public void createReqOrClaimSubjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reqOrClaimSubjectRepository.findAll().size();

        // Create the ReqOrClaimSubject with an existing ID
        reqOrClaimSubject.setId("existing_id");
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO = reqOrClaimSubjectMapper.toDto(reqOrClaimSubject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReqOrClaimSubjectMockMvc.perform(post("/api/req-or-claim-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reqOrClaimSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReqOrClaimSubject in the database
        List<ReqOrClaimSubject> reqOrClaimSubjectList = reqOrClaimSubjectRepository.findAll();
        assertThat(reqOrClaimSubjectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllReqOrClaimSubjects() throws Exception {
        // Initialize the database
        reqOrClaimSubjectRepository.save(reqOrClaimSubject);

        // Get all the reqOrClaimSubjectList
        restReqOrClaimSubjectMockMvc.perform(get("/api/req-or-claim-subjects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reqOrClaimSubject.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].decsription").value(hasItem(DEFAULT_DECSRIPTION)));
    }
    
    @Test
    public void getReqOrClaimSubject() throws Exception {
        // Initialize the database
        reqOrClaimSubjectRepository.save(reqOrClaimSubject);

        // Get the reqOrClaimSubject
        restReqOrClaimSubjectMockMvc.perform(get("/api/req-or-claim-subjects/{id}", reqOrClaimSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reqOrClaimSubject.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.decsription").value(DEFAULT_DECSRIPTION));
    }

    @Test
    public void getNonExistingReqOrClaimSubject() throws Exception {
        // Get the reqOrClaimSubject
        restReqOrClaimSubjectMockMvc.perform(get("/api/req-or-claim-subjects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateReqOrClaimSubject() throws Exception {
        // Initialize the database
        reqOrClaimSubjectRepository.save(reqOrClaimSubject);

        int databaseSizeBeforeUpdate = reqOrClaimSubjectRepository.findAll().size();

        // Update the reqOrClaimSubject
        ReqOrClaimSubject updatedReqOrClaimSubject = reqOrClaimSubjectRepository.findById(reqOrClaimSubject.getId()).get();
        updatedReqOrClaimSubject
            .name(UPDATED_NAME)
            .decsription(UPDATED_DECSRIPTION);
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO = reqOrClaimSubjectMapper.toDto(updatedReqOrClaimSubject);

        restReqOrClaimSubjectMockMvc.perform(put("/api/req-or-claim-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reqOrClaimSubjectDTO)))
            .andExpect(status().isOk());

        // Validate the ReqOrClaimSubject in the database
        List<ReqOrClaimSubject> reqOrClaimSubjectList = reqOrClaimSubjectRepository.findAll();
        assertThat(reqOrClaimSubjectList).hasSize(databaseSizeBeforeUpdate);
        ReqOrClaimSubject testReqOrClaimSubject = reqOrClaimSubjectList.get(reqOrClaimSubjectList.size() - 1);
        assertThat(testReqOrClaimSubject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testReqOrClaimSubject.getDecsription()).isEqualTo(UPDATED_DECSRIPTION);
    }

    @Test
    public void updateNonExistingReqOrClaimSubject() throws Exception {
        int databaseSizeBeforeUpdate = reqOrClaimSubjectRepository.findAll().size();

        // Create the ReqOrClaimSubject
        ReqOrClaimSubjectDTO reqOrClaimSubjectDTO = reqOrClaimSubjectMapper.toDto(reqOrClaimSubject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReqOrClaimSubjectMockMvc.perform(put("/api/req-or-claim-subjects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reqOrClaimSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReqOrClaimSubject in the database
        List<ReqOrClaimSubject> reqOrClaimSubjectList = reqOrClaimSubjectRepository.findAll();
        assertThat(reqOrClaimSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteReqOrClaimSubject() throws Exception {
        // Initialize the database
        reqOrClaimSubjectRepository.save(reqOrClaimSubject);

        int databaseSizeBeforeDelete = reqOrClaimSubjectRepository.findAll().size();

        // Delete the reqOrClaimSubject
        restReqOrClaimSubjectMockMvc.perform(delete("/api/req-or-claim-subjects/{id}", reqOrClaimSubject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReqOrClaimSubject> reqOrClaimSubjectList = reqOrClaimSubjectRepository.findAll();
        assertThat(reqOrClaimSubjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
