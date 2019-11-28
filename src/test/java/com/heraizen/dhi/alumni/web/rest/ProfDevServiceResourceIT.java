package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.ProfDevService;
import com.heraizen.dhi.alumni.repository.ProfDevServiceRepository;
import com.heraizen.dhi.alumni.service.ProfDevServiceService;
import com.heraizen.dhi.alumni.service.dto.ProfDevServiceDTO;
import com.heraizen.dhi.alumni.service.mapper.ProfDevServiceMapper;
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
 * Integration tests for the {@link ProfDevServiceResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class ProfDevServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProfDevServiceRepository profDevServiceRepository;

    @Autowired
    private ProfDevServiceMapper profDevServiceMapper;

    @Autowired
    private ProfDevServiceService profDevServiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProfDevServiceMockMvc;

    private ProfDevService profDevService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfDevServiceResource profDevServiceResource = new ProfDevServiceResource(profDevServiceService);
        this.restProfDevServiceMockMvc = MockMvcBuilders.standaloneSetup(profDevServiceResource)
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
    public static ProfDevService createEntity() {
        ProfDevService profDevService = new ProfDevService()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return profDevService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfDevService createUpdatedEntity() {
        ProfDevService profDevService = new ProfDevService()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return profDevService;
    }

    @BeforeEach
    public void initTest() {
        profDevServiceRepository.deleteAll();
        profDevService = createEntity();
    }

    @Test
    public void createProfDevService() throws Exception {
        int databaseSizeBeforeCreate = profDevServiceRepository.findAll().size();

        // Create the ProfDevService
        ProfDevServiceDTO profDevServiceDTO = profDevServiceMapper.toDto(profDevService);
        restProfDevServiceMockMvc.perform(post("/api/prof-dev-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profDevServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfDevService in the database
        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ProfDevService testProfDevService = profDevServiceList.get(profDevServiceList.size() - 1);
        assertThat(testProfDevService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProfDevService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createProfDevServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profDevServiceRepository.findAll().size();

        // Create the ProfDevService with an existing ID
        profDevService.setId("existing_id");
        ProfDevServiceDTO profDevServiceDTO = profDevServiceMapper.toDto(profDevService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfDevServiceMockMvc.perform(post("/api/prof-dev-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profDevServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfDevService in the database
        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profDevServiceRepository.findAll().size();
        // set the field null
        profDevService.setName(null);

        // Create the ProfDevService, which fails.
        ProfDevServiceDTO profDevServiceDTO = profDevServiceMapper.toDto(profDevService);

        restProfDevServiceMockMvc.perform(post("/api/prof-dev-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profDevServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProfDevServices() throws Exception {
        // Initialize the database
        profDevServiceRepository.save(profDevService);

        // Get all the profDevServiceList
        restProfDevServiceMockMvc.perform(get("/api/prof-dev-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profDevService.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    public void getProfDevService() throws Exception {
        // Initialize the database
        profDevServiceRepository.save(profDevService);

        // Get the profDevService
        restProfDevServiceMockMvc.perform(get("/api/prof-dev-services/{id}", profDevService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profDevService.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    public void getNonExistingProfDevService() throws Exception {
        // Get the profDevService
        restProfDevServiceMockMvc.perform(get("/api/prof-dev-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProfDevService() throws Exception {
        // Initialize the database
        profDevServiceRepository.save(profDevService);

        int databaseSizeBeforeUpdate = profDevServiceRepository.findAll().size();

        // Update the profDevService
        ProfDevService updatedProfDevService = profDevServiceRepository.findById(profDevService.getId()).get();
        updatedProfDevService
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        ProfDevServiceDTO profDevServiceDTO = profDevServiceMapper.toDto(updatedProfDevService);

        restProfDevServiceMockMvc.perform(put("/api/prof-dev-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profDevServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ProfDevService in the database
        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeUpdate);
        ProfDevService testProfDevService = profDevServiceList.get(profDevServiceList.size() - 1);
        assertThat(testProfDevService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProfDevService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingProfDevService() throws Exception {
        int databaseSizeBeforeUpdate = profDevServiceRepository.findAll().size();

        // Create the ProfDevService
        ProfDevServiceDTO profDevServiceDTO = profDevServiceMapper.toDto(profDevService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfDevServiceMockMvc.perform(put("/api/prof-dev-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profDevServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfDevService in the database
        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteProfDevService() throws Exception {
        // Initialize the database
        profDevServiceRepository.save(profDevService);

        int databaseSizeBeforeDelete = profDevServiceRepository.findAll().size();

        // Delete the profDevService
        restProfDevServiceMockMvc.perform(delete("/api/prof-dev-services/{id}", profDevService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfDevService> profDevServiceList = profDevServiceRepository.findAll();
        assertThat(profDevServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
