package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.Fundraiser;
import com.heraizen.dhi.alumni.repository.FundraiserRepository;
import com.heraizen.dhi.alumni.service.FundraiserService;
import com.heraizen.dhi.alumni.service.dto.FundraiserDTO;
import com.heraizen.dhi.alumni.service.mapper.FundraiserMapper;
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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.heraizen.dhi.alumni.domain.enumeration.FundraiseStatus;
/**
 * Integration tests for the {@link FundraiserResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class FundraiserResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_TARGET_AMOUNT = 1D;
    private static final Double UPDATED_TARGET_AMOUNT = 2D;

    private static final Double DEFAULT_COLLECTED_AMOUNT = 1D;
    private static final Double UPDATED_COLLECTED_AMOUNT = 2D;

    private static final LocalDate DEFAULT_LAST_DATE_TO_CONTRIBUTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_DATE_TO_CONTRIBUTE = LocalDate.now(ZoneId.systemDefault());

    private static final FundraiseStatus DEFAULT_STATUS = FundraiseStatus.ACHIEVED;
    private static final FundraiseStatus UPDATED_STATUS = FundraiseStatus.YET_TO_ACHIEVE;

    @Autowired
    private FundraiserRepository fundraiserRepository;

    @Autowired
    private FundraiserMapper fundraiserMapper;

    @Autowired
    private FundraiserService fundraiserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restFundraiserMockMvc;

    private Fundraiser fundraiser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FundraiserResource fundraiserResource = new FundraiserResource(fundraiserService);
        this.restFundraiserMockMvc = MockMvcBuilders.standaloneSetup(fundraiserResource)
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
    public static Fundraiser createEntity() {
        Fundraiser fundraiser = new Fundraiser()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .targetAmount(DEFAULT_TARGET_AMOUNT)
            .collectedAmount(DEFAULT_COLLECTED_AMOUNT)
            .lastDateToContribute(DEFAULT_LAST_DATE_TO_CONTRIBUTE)
            .status(DEFAULT_STATUS);
        return fundraiser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundraiser createUpdatedEntity() {
        Fundraiser fundraiser = new Fundraiser()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .targetAmount(UPDATED_TARGET_AMOUNT)
            .collectedAmount(UPDATED_COLLECTED_AMOUNT)
            .lastDateToContribute(UPDATED_LAST_DATE_TO_CONTRIBUTE)
            .status(UPDATED_STATUS);
        return fundraiser;
    }

    @BeforeEach
    public void initTest() {
        fundraiserRepository.deleteAll();
        fundraiser = createEntity();
    }

    @Test
    public void createFundraiser() throws Exception {
        int databaseSizeBeforeCreate = fundraiserRepository.findAll().size();

        // Create the Fundraiser
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(fundraiser);
        restFundraiserMockMvc.perform(post("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isCreated());

        // Validate the Fundraiser in the database
        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeCreate + 1);
        Fundraiser testFundraiser = fundraiserList.get(fundraiserList.size() - 1);
        assertThat(testFundraiser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFundraiser.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFundraiser.getTargetAmount()).isEqualTo(DEFAULT_TARGET_AMOUNT);
        assertThat(testFundraiser.getCollectedAmount()).isEqualTo(DEFAULT_COLLECTED_AMOUNT);
        assertThat(testFundraiser.getLastDateToContribute()).isEqualTo(DEFAULT_LAST_DATE_TO_CONTRIBUTE);
        assertThat(testFundraiser.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    public void createFundraiserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundraiserRepository.findAll().size();

        // Create the Fundraiser with an existing ID
        fundraiser.setId("existing_id");
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(fundraiser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundraiserMockMvc.perform(post("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fundraiser in the database
        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundraiserRepository.findAll().size();
        // set the field null
        fundraiser.setName(null);

        // Create the Fundraiser, which fails.
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(fundraiser);

        restFundraiserMockMvc.perform(post("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isBadRequest());

        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTargetAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundraiserRepository.findAll().size();
        // set the field null
        fundraiser.setTargetAmount(null);

        // Create the Fundraiser, which fails.
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(fundraiser);

        restFundraiserMockMvc.perform(post("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isBadRequest());

        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllFundraisers() throws Exception {
        // Initialize the database
        fundraiserRepository.save(fundraiser);

        // Get all the fundraiserList
        restFundraiserMockMvc.perform(get("/api/fundraisers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundraiser.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].targetAmount").value(hasItem(DEFAULT_TARGET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].collectedAmount").value(hasItem(DEFAULT_COLLECTED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].lastDateToContribute").value(hasItem(DEFAULT_LAST_DATE_TO_CONTRIBUTE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    public void getFundraiser() throws Exception {
        // Initialize the database
        fundraiserRepository.save(fundraiser);

        // Get the fundraiser
        restFundraiserMockMvc.perform(get("/api/fundraisers/{id}", fundraiser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fundraiser.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.targetAmount").value(DEFAULT_TARGET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.collectedAmount").value(DEFAULT_COLLECTED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.lastDateToContribute").value(DEFAULT_LAST_DATE_TO_CONTRIBUTE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    public void getNonExistingFundraiser() throws Exception {
        // Get the fundraiser
        restFundraiserMockMvc.perform(get("/api/fundraisers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFundraiser() throws Exception {
        // Initialize the database
        fundraiserRepository.save(fundraiser);

        int databaseSizeBeforeUpdate = fundraiserRepository.findAll().size();

        // Update the fundraiser
        Fundraiser updatedFundraiser = fundraiserRepository.findById(fundraiser.getId()).get();
        updatedFundraiser
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .targetAmount(UPDATED_TARGET_AMOUNT)
            .collectedAmount(UPDATED_COLLECTED_AMOUNT)
            .lastDateToContribute(UPDATED_LAST_DATE_TO_CONTRIBUTE)
            .status(UPDATED_STATUS);
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(updatedFundraiser);

        restFundraiserMockMvc.perform(put("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isOk());

        // Validate the Fundraiser in the database
        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeUpdate);
        Fundraiser testFundraiser = fundraiserList.get(fundraiserList.size() - 1);
        assertThat(testFundraiser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFundraiser.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFundraiser.getTargetAmount()).isEqualTo(UPDATED_TARGET_AMOUNT);
        assertThat(testFundraiser.getCollectedAmount()).isEqualTo(UPDATED_COLLECTED_AMOUNT);
        assertThat(testFundraiser.getLastDateToContribute()).isEqualTo(UPDATED_LAST_DATE_TO_CONTRIBUTE);
        assertThat(testFundraiser.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    public void updateNonExistingFundraiser() throws Exception {
        int databaseSizeBeforeUpdate = fundraiserRepository.findAll().size();

        // Create the Fundraiser
        FundraiserDTO fundraiserDTO = fundraiserMapper.toDto(fundraiser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundraiserMockMvc.perform(put("/api/fundraisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundraiserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fundraiser in the database
        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFundraiser() throws Exception {
        // Initialize the database
        fundraiserRepository.save(fundraiser);

        int databaseSizeBeforeDelete = fundraiserRepository.findAll().size();

        // Delete the fundraiser
        restFundraiserMockMvc.perform(delete("/api/fundraisers/{id}", fundraiser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fundraiser> fundraiserList = fundraiserRepository.findAll();
        assertThat(fundraiserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
