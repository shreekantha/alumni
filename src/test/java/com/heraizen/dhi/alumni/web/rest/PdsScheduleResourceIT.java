package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.PdsSchedule;
import com.heraizen.dhi.alumni.domain.ProfDevService;
import com.heraizen.dhi.alumni.repository.PdsScheduleRepository;
import com.heraizen.dhi.alumni.service.PdsScheduleService;
import com.heraizen.dhi.alumni.service.dto.PdsScheduleDTO;
import com.heraizen.dhi.alumni.service.mapper.PdsScheduleMapper;
import com.heraizen.dhi.alumni.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Duration;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PdsScheduleResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class PdsScheduleResourceIT {

    private static final Duration DEFAULT_DURATION = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION = Duration.ofHours(12);

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VENUE = "AAAAAAAAAA";
    private static final String UPDATED_VENUE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private PdsScheduleRepository pdsScheduleRepository;

    @Mock
    private PdsScheduleRepository pdsScheduleRepositoryMock;

    @Autowired
    private PdsScheduleMapper pdsScheduleMapper;

    @Mock
    private PdsScheduleService pdsScheduleServiceMock;

    @Autowired
    private PdsScheduleService pdsScheduleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPdsScheduleMockMvc;

    private PdsSchedule pdsSchedule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PdsScheduleResource pdsScheduleResource = new PdsScheduleResource(pdsScheduleService);
        this.restPdsScheduleMockMvc = MockMvcBuilders.standaloneSetup(pdsScheduleResource)
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
    public static PdsSchedule createEntity() {
        PdsSchedule pdsSchedule = new PdsSchedule()
            .duration(DEFAULT_DURATION)
            .date(DEFAULT_DATE)
            .time(DEFAULT_TIME)
            .venue(DEFAULT_VENUE)
            .remarks(DEFAULT_REMARKS);
        // Add required entity
        ProfDevService profDevService;
        profDevService = ProfDevServiceResourceIT.createEntity();
        profDevService.setId("fixed-id-for-tests");
        pdsSchedule.setProfDevService(profDevService);
        return pdsSchedule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdsSchedule createUpdatedEntity() {
        PdsSchedule pdsSchedule = new PdsSchedule()
            .duration(UPDATED_DURATION)
            .date(UPDATED_DATE)
            .time(UPDATED_TIME)
            .venue(UPDATED_VENUE)
            .remarks(UPDATED_REMARKS);
        // Add required entity
        ProfDevService profDevService;
        profDevService = ProfDevServiceResourceIT.createUpdatedEntity();
        profDevService.setId("fixed-id-for-tests");
        pdsSchedule.setProfDevService(profDevService);
        return pdsSchedule;
    }

    @BeforeEach
    public void initTest() {
        pdsScheduleRepository.deleteAll();
        pdsSchedule = createEntity();
    }

    @Test
    public void createPdsSchedule() throws Exception {
        int databaseSizeBeforeCreate = pdsScheduleRepository.findAll().size();

        // Create the PdsSchedule
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);
        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isCreated());

        // Validate the PdsSchedule in the database
        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeCreate + 1);
        PdsSchedule testPdsSchedule = pdsScheduleList.get(pdsScheduleList.size() - 1);
        assertThat(testPdsSchedule.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testPdsSchedule.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testPdsSchedule.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testPdsSchedule.getVenue()).isEqualTo(DEFAULT_VENUE);
        assertThat(testPdsSchedule.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    public void createPdsScheduleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdsScheduleRepository.findAll().size();

        // Create the PdsSchedule with an existing ID
        pdsSchedule.setId("existing_id");
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsSchedule in the database
        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkDurationIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdsScheduleRepository.findAll().size();
        // set the field null
        pdsSchedule.setDuration(null);

        // Create the PdsSchedule, which fails.
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdsScheduleRepository.findAll().size();
        // set the field null
        pdsSchedule.setDate(null);

        // Create the PdsSchedule, which fails.
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdsScheduleRepository.findAll().size();
        // set the field null
        pdsSchedule.setTime(null);

        // Create the PdsSchedule, which fails.
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVenueIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdsScheduleRepository.findAll().size();
        // set the field null
        pdsSchedule.setVenue(null);

        // Create the PdsSchedule, which fails.
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        restPdsScheduleMockMvc.perform(post("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPdsSchedules() throws Exception {
        // Initialize the database
        pdsScheduleRepository.save(pdsSchedule);

        // Get all the pdsScheduleList
        restPdsScheduleMockMvc.perform(get("/api/pds-schedules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdsSchedule.getId())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].venue").value(hasItem(DEFAULT_VENUE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPdsSchedulesWithEagerRelationshipsIsEnabled() throws Exception {
        PdsScheduleResource pdsScheduleResource = new PdsScheduleResource(pdsScheduleServiceMock);
        when(pdsScheduleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restPdsScheduleMockMvc = MockMvcBuilders.standaloneSetup(pdsScheduleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPdsScheduleMockMvc.perform(get("/api/pds-schedules?eagerload=true"))
        .andExpect(status().isOk());

        verify(pdsScheduleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPdsSchedulesWithEagerRelationshipsIsNotEnabled() throws Exception {
        PdsScheduleResource pdsScheduleResource = new PdsScheduleResource(pdsScheduleServiceMock);
            when(pdsScheduleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restPdsScheduleMockMvc = MockMvcBuilders.standaloneSetup(pdsScheduleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPdsScheduleMockMvc.perform(get("/api/pds-schedules?eagerload=true"))
        .andExpect(status().isOk());

            verify(pdsScheduleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getPdsSchedule() throws Exception {
        // Initialize the database
        pdsScheduleRepository.save(pdsSchedule);

        // Get the pdsSchedule
        restPdsScheduleMockMvc.perform(get("/api/pds-schedules/{id}", pdsSchedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pdsSchedule.getId()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.venue").value(DEFAULT_VENUE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }

    @Test
    public void getNonExistingPdsSchedule() throws Exception {
        // Get the pdsSchedule
        restPdsScheduleMockMvc.perform(get("/api/pds-schedules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePdsSchedule() throws Exception {
        // Initialize the database
        pdsScheduleRepository.save(pdsSchedule);

        int databaseSizeBeforeUpdate = pdsScheduleRepository.findAll().size();

        // Update the pdsSchedule
        PdsSchedule updatedPdsSchedule = pdsScheduleRepository.findById(pdsSchedule.getId()).get();
        updatedPdsSchedule
            .duration(UPDATED_DURATION)
            .date(UPDATED_DATE)
            .time(UPDATED_TIME)
            .venue(UPDATED_VENUE)
            .remarks(UPDATED_REMARKS);
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(updatedPdsSchedule);

        restPdsScheduleMockMvc.perform(put("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isOk());

        // Validate the PdsSchedule in the database
        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeUpdate);
        PdsSchedule testPdsSchedule = pdsScheduleList.get(pdsScheduleList.size() - 1);
        assertThat(testPdsSchedule.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testPdsSchedule.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testPdsSchedule.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testPdsSchedule.getVenue()).isEqualTo(UPDATED_VENUE);
        assertThat(testPdsSchedule.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    public void updateNonExistingPdsSchedule() throws Exception {
        int databaseSizeBeforeUpdate = pdsScheduleRepository.findAll().size();

        // Create the PdsSchedule
        PdsScheduleDTO pdsScheduleDTO = pdsScheduleMapper.toDto(pdsSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdsScheduleMockMvc.perform(put("/api/pds-schedules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pdsScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PdsSchedule in the database
        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePdsSchedule() throws Exception {
        // Initialize the database
        pdsScheduleRepository.save(pdsSchedule);

        int databaseSizeBeforeDelete = pdsScheduleRepository.findAll().size();

        // Delete the pdsSchedule
        restPdsScheduleMockMvc.perform(delete("/api/pds-schedules/{id}", pdsSchedule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdsSchedule> pdsScheduleList = pdsScheduleRepository.findAll();
        assertThat(pdsScheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
