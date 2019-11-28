package com.heraizen.dhi.alumni.web.rest;

import com.heraizen.dhi.alumni.DhiAlumniApp;
import com.heraizen.dhi.alumni.domain.FundContribution;
import com.heraizen.dhi.alumni.domain.User;
import com.heraizen.dhi.alumni.domain.Fundraiser;
import com.heraizen.dhi.alumni.repository.FundContributionRepository;
import com.heraizen.dhi.alumni.service.FundContributionService;
import com.heraizen.dhi.alumni.service.dto.FundContributionDTO;
import com.heraizen.dhi.alumni.service.mapper.FundContributionMapper;
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


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.heraizen.dhi.alumni.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.heraizen.dhi.alumni.domain.enumeration.Currency;
/**
 * Integration tests for the {@link FundContributionResource} REST controller.
 */
@SpringBootTest(classes = DhiAlumniApp.class)
public class FundContributionResourceIT {

    private static final Currency DEFAULT_CURRENCY = Currency.INR;
    private static final Currency UPDATED_CURRENCY = Currency.USD;

    private static final Double DEFAULT_CONTR_AMOUNT = 1D;
    private static final Double UPDATED_CONTR_AMOUNT = 2D;

    private static final Instant DEFAULT_CONTR_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CONTR_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FundContributionRepository fundContributionRepository;

    @Autowired
    private FundContributionMapper fundContributionMapper;

    @Autowired
    private FundContributionService fundContributionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restFundContributionMockMvc;

    private FundContribution fundContribution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FundContributionResource fundContributionResource = new FundContributionResource(fundContributionService);
        this.restFundContributionMockMvc = MockMvcBuilders.standaloneSetup(fundContributionResource)
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
    public static FundContribution createEntity() {
        FundContribution fundContribution = new FundContribution()
            .currency(DEFAULT_CURRENCY)
            .contrAmount(DEFAULT_CONTR_AMOUNT)
            .contrDate(DEFAULT_CONTR_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        fundContribution.setContibutor(user);
        // Add required entity
        Fundraiser fundraiser;
        fundraiser = FundraiserResourceIT.createEntity();
        fundraiser.setId("fixed-id-for-tests");
        fundContribution.setFundraiser(fundraiser);
        return fundContribution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundContribution createUpdatedEntity() {
        FundContribution fundContribution = new FundContribution()
            .currency(UPDATED_CURRENCY)
            .contrAmount(UPDATED_CONTR_AMOUNT)
            .contrDate(UPDATED_CONTR_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        fundContribution.setContibutor(user);
        // Add required entity
        Fundraiser fundraiser;
        fundraiser = FundraiserResourceIT.createUpdatedEntity();
        fundraiser.setId("fixed-id-for-tests");
        fundContribution.setFundraiser(fundraiser);
        return fundContribution;
    }

    @BeforeEach
    public void initTest() {
        fundContributionRepository.deleteAll();
        fundContribution = createEntity();
    }

    @Test
    public void createFundContribution() throws Exception {
        int databaseSizeBeforeCreate = fundContributionRepository.findAll().size();

        // Create the FundContribution
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(fundContribution);
        restFundContributionMockMvc.perform(post("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isCreated());

        // Validate the FundContribution in the database
        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeCreate + 1);
        FundContribution testFundContribution = fundContributionList.get(fundContributionList.size() - 1);
        assertThat(testFundContribution.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFundContribution.getContrAmount()).isEqualTo(DEFAULT_CONTR_AMOUNT);
        assertThat(testFundContribution.getContrDate()).isEqualTo(DEFAULT_CONTR_DATE);
    }

    @Test
    public void createFundContributionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundContributionRepository.findAll().size();

        // Create the FundContribution with an existing ID
        fundContribution.setId("existing_id");
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(fundContribution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundContributionMockMvc.perform(post("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundContribution in the database
        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundContributionRepository.findAll().size();
        // set the field null
        fundContribution.setCurrency(null);

        // Create the FundContribution, which fails.
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(fundContribution);

        restFundContributionMockMvc.perform(post("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isBadRequest());

        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkContrAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundContributionRepository.findAll().size();
        // set the field null
        fundContribution.setContrAmount(null);

        // Create the FundContribution, which fails.
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(fundContribution);

        restFundContributionMockMvc.perform(post("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isBadRequest());

        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllFundContributions() throws Exception {
        // Initialize the database
        fundContributionRepository.save(fundContribution);

        // Get all the fundContributionList
        restFundContributionMockMvc.perform(get("/api/fund-contributions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundContribution.getId())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].contrAmount").value(hasItem(DEFAULT_CONTR_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].contrDate").value(hasItem(DEFAULT_CONTR_DATE.toString())));
    }
    
    @Test
    public void getFundContribution() throws Exception {
        // Initialize the database
        fundContributionRepository.save(fundContribution);

        // Get the fundContribution
        restFundContributionMockMvc.perform(get("/api/fund-contributions/{id}", fundContribution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fundContribution.getId()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.contrAmount").value(DEFAULT_CONTR_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.contrDate").value(DEFAULT_CONTR_DATE.toString()));
    }

    @Test
    public void getNonExistingFundContribution() throws Exception {
        // Get the fundContribution
        restFundContributionMockMvc.perform(get("/api/fund-contributions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFundContribution() throws Exception {
        // Initialize the database
        fundContributionRepository.save(fundContribution);

        int databaseSizeBeforeUpdate = fundContributionRepository.findAll().size();

        // Update the fundContribution
        FundContribution updatedFundContribution = fundContributionRepository.findById(fundContribution.getId()).get();
        updatedFundContribution
            .currency(UPDATED_CURRENCY)
            .contrAmount(UPDATED_CONTR_AMOUNT)
            .contrDate(UPDATED_CONTR_DATE);
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(updatedFundContribution);

        restFundContributionMockMvc.perform(put("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isOk());

        // Validate the FundContribution in the database
        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeUpdate);
        FundContribution testFundContribution = fundContributionList.get(fundContributionList.size() - 1);
        assertThat(testFundContribution.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundContribution.getContrAmount()).isEqualTo(UPDATED_CONTR_AMOUNT);
        assertThat(testFundContribution.getContrDate()).isEqualTo(UPDATED_CONTR_DATE);
    }

    @Test
    public void updateNonExistingFundContribution() throws Exception {
        int databaseSizeBeforeUpdate = fundContributionRepository.findAll().size();

        // Create the FundContribution
        FundContributionDTO fundContributionDTO = fundContributionMapper.toDto(fundContribution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundContributionMockMvc.perform(put("/api/fund-contributions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundContributionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundContribution in the database
        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFundContribution() throws Exception {
        // Initialize the database
        fundContributionRepository.save(fundContribution);

        int databaseSizeBeforeDelete = fundContributionRepository.findAll().size();

        // Delete the fundContribution
        restFundContributionMockMvc.perform(delete("/api/fund-contributions/{id}", fundContribution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FundContribution> fundContributionList = fundContributionRepository.findAll();
        assertThat(fundContributionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
