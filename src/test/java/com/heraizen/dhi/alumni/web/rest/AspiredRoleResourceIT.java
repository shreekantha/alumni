package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.AspiredRole;
import com.heraizen.dhi.alumni.repository.AspiredRoleRepository;
import com.heraizen.dhi.alumni.service.AspiredRoleService;
import com.heraizen.dhi.alumni.service.dto.AspiredRoleDTO;
import com.heraizen.dhi.alumni.service.mapper.AspiredRoleMapper;
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
 * Integration tests for the {@link AspiredRoleResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class AspiredRoleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AspiredRoleRepository aspiredRoleRepository;

    @Autowired
    private AspiredRoleMapper aspiredRoleMapper;

    @Autowired
    private AspiredRoleService aspiredRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAspiredRoleMockMvc;

    private AspiredRole aspiredRole;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AspiredRoleResource aspiredRoleResource = new AspiredRoleResource(aspiredRoleService);
        this.restAspiredRoleMockMvc = MockMvcBuilders.standaloneSetup(aspiredRoleResource)
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
    public static AspiredRole createEntity() {
        AspiredRole aspiredRole = new AspiredRole()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return aspiredRole;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AspiredRole createUpdatedEntity() {
        AspiredRole aspiredRole = new AspiredRole()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return aspiredRole;
    }

    @BeforeEach
    public void initTest() {
        aspiredRoleRepository.deleteAll();
        aspiredRole = createEntity();
    }

    @Test
    public void createAspiredRole() throws Exception {
        int databaseSizeBeforeCreate = aspiredRoleRepository.findAll().size();

        // Create the AspiredRole
        AspiredRoleDTO aspiredRoleDTO = aspiredRoleMapper.toDto(aspiredRole);
        restAspiredRoleMockMvc.perform(post("/api/aspired-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aspiredRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the AspiredRole in the database
        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeCreate + 1);
        AspiredRole testAspiredRole = aspiredRoleList.get(aspiredRoleList.size() - 1);
        assertThat(testAspiredRole.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAspiredRole.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createAspiredRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aspiredRoleRepository.findAll().size();

        // Create the AspiredRole with an existing ID
        aspiredRole.setId("existing_id");
        AspiredRoleDTO aspiredRoleDTO = aspiredRoleMapper.toDto(aspiredRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAspiredRoleMockMvc.perform(post("/api/aspired-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aspiredRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AspiredRole in the database
        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = aspiredRoleRepository.findAll().size();
        // set the field null
        aspiredRole.setName(null);

        // Create the AspiredRole, which fails.
        AspiredRoleDTO aspiredRoleDTO = aspiredRoleMapper.toDto(aspiredRole);

        restAspiredRoleMockMvc.perform(post("/api/aspired-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aspiredRoleDTO)))
            .andExpect(status().isBadRequest());

        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAspiredRoles() throws Exception {
        // Initialize the database
        aspiredRoleRepository.save(aspiredRole);

        // Get all the aspiredRoleList
        restAspiredRoleMockMvc.perform(get("/api/aspired-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aspiredRole.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    public void getAspiredRole() throws Exception {
        // Initialize the database
        aspiredRoleRepository.save(aspiredRole);

        // Get the aspiredRole
        restAspiredRoleMockMvc.perform(get("/api/aspired-roles/{id}", aspiredRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aspiredRole.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    public void getNonExistingAspiredRole() throws Exception {
        // Get the aspiredRole
        restAspiredRoleMockMvc.perform(get("/api/aspired-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAspiredRole() throws Exception {
        // Initialize the database
        aspiredRoleRepository.save(aspiredRole);

        int databaseSizeBeforeUpdate = aspiredRoleRepository.findAll().size();

        // Update the aspiredRole
        AspiredRole updatedAspiredRole = aspiredRoleRepository.findById(aspiredRole.getId()).get();
        updatedAspiredRole
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        AspiredRoleDTO aspiredRoleDTO = aspiredRoleMapper.toDto(updatedAspiredRole);

        restAspiredRoleMockMvc.perform(put("/api/aspired-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aspiredRoleDTO)))
            .andExpect(status().isOk());

        // Validate the AspiredRole in the database
        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeUpdate);
        AspiredRole testAspiredRole = aspiredRoleList.get(aspiredRoleList.size() - 1);
        assertThat(testAspiredRole.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAspiredRole.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingAspiredRole() throws Exception {
        int databaseSizeBeforeUpdate = aspiredRoleRepository.findAll().size();

        // Create the AspiredRole
        AspiredRoleDTO aspiredRoleDTO = aspiredRoleMapper.toDto(aspiredRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAspiredRoleMockMvc.perform(put("/api/aspired-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aspiredRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AspiredRole in the database
        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAspiredRole() throws Exception {
        // Initialize the database
        aspiredRoleRepository.save(aspiredRole);

        int databaseSizeBeforeDelete = aspiredRoleRepository.findAll().size();

        // Delete the aspiredRole
        restAspiredRoleMockMvc.perform(delete("/api/aspired-roles/{id}", aspiredRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AspiredRole> aspiredRoleList = aspiredRoleRepository.findAll();
        assertThat(aspiredRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
