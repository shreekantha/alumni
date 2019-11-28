package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.Degree;
import com.heraizen.dhi.alumni.repository.DegreeRepository;
import com.heraizen.dhi.alumni.service.DegreeService;
import com.heraizen.dhi.alumni.service.dto.DegreeDTO;
import com.heraizen.dhi.alumni.service.mapper.DegreeMapper;
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
 * Integration tests for the {@link DegreeResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class DegreeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private DegreeMapper degreeMapper;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDegreeMockMvc;

    private Degree degree;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DegreeResource degreeResource = new DegreeResource(degreeService);
        this.restDegreeMockMvc = MockMvcBuilders.standaloneSetup(degreeResource)
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
    public static Degree createEntity() {
        Degree degree = new Degree()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME);
        return degree;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Degree createUpdatedEntity() {
        Degree degree = new Degree()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME);
        return degree;
    }

    @BeforeEach
    public void initTest() {
        degreeRepository.deleteAll();
        degree = createEntity();
    }

    @Test
    public void createDegree() throws Exception {
        int databaseSizeBeforeCreate = degreeRepository.findAll().size();

        // Create the Degree
        DegreeDTO degreeDTO = degreeMapper.toDto(degree);
        restDegreeMockMvc.perform(post("/api/degrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(degreeDTO)))
            .andExpect(status().isCreated());

        // Validate the Degree in the database
        List<Degree> degreeList = degreeRepository.findAll();
        assertThat(degreeList).hasSize(databaseSizeBeforeCreate + 1);
        Degree testDegree = degreeList.get(degreeList.size() - 1);
        assertThat(testDegree.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDegree.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
    }

    @Test
    public void createDegreeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = degreeRepository.findAll().size();

        // Create the Degree with an existing ID
        degree.setId("existing_id");
        DegreeDTO degreeDTO = degreeMapper.toDto(degree);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDegreeMockMvc.perform(post("/api/degrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(degreeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Degree in the database
        List<Degree> degreeList = degreeRepository.findAll();
        assertThat(degreeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllDegrees() throws Exception {
        // Initialize the database
        degreeRepository.save(degree);

        // Get all the degreeList
        restDegreeMockMvc.perform(get("/api/degrees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(degree.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)));
    }
    
    @Test
    public void getDegree() throws Exception {
        // Initialize the database
        degreeRepository.save(degree);

        // Get the degree
        restDegreeMockMvc.perform(get("/api/degrees/{id}", degree.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(degree.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME));
    }

    @Test
    public void getNonExistingDegree() throws Exception {
        // Get the degree
        restDegreeMockMvc.perform(get("/api/degrees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDegree() throws Exception {
        // Initialize the database
        degreeRepository.save(degree);

        int databaseSizeBeforeUpdate = degreeRepository.findAll().size();

        // Update the degree
        Degree updatedDegree = degreeRepository.findById(degree.getId()).get();
        updatedDegree
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME);
        DegreeDTO degreeDTO = degreeMapper.toDto(updatedDegree);

        restDegreeMockMvc.perform(put("/api/degrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(degreeDTO)))
            .andExpect(status().isOk());

        // Validate the Degree in the database
        List<Degree> degreeList = degreeRepository.findAll();
        assertThat(degreeList).hasSize(databaseSizeBeforeUpdate);
        Degree testDegree = degreeList.get(degreeList.size() - 1);
        assertThat(testDegree.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDegree.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
    }

    @Test
    public void updateNonExistingDegree() throws Exception {
        int databaseSizeBeforeUpdate = degreeRepository.findAll().size();

        // Create the Degree
        DegreeDTO degreeDTO = degreeMapper.toDto(degree);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDegreeMockMvc.perform(put("/api/degrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(degreeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Degree in the database
        List<Degree> degreeList = degreeRepository.findAll();
        assertThat(degreeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDegree() throws Exception {
        // Initialize the database
        degreeRepository.save(degree);

        int databaseSizeBeforeDelete = degreeRepository.findAll().size();

        // Delete the degree
        restDegreeMockMvc.perform(delete("/api/degrees/{id}", degree.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Degree> degreeList = degreeRepository.findAll();
        assertThat(degreeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
