package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.FundraiserService;
import com.heraizen.dhi.alumni.domain.Fundraiser;
import com.heraizen.dhi.alumni.repository.FundraiserRepository;
import com.heraizen.dhi.alumni.service.dto.FundraiserDTO;
import com.heraizen.dhi.alumni.service.mapper.FundraiserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Fundraiser}.
 */
@Service
public class FundraiserServiceImpl implements FundraiserService {

    private final Logger log = LoggerFactory.getLogger(FundraiserServiceImpl.class);

    private final FundraiserRepository fundraiserRepository;

    private final FundraiserMapper fundraiserMapper;

    public FundraiserServiceImpl(FundraiserRepository fundraiserRepository, FundraiserMapper fundraiserMapper) {
        this.fundraiserRepository = fundraiserRepository;
        this.fundraiserMapper = fundraiserMapper;
    }

    /**
     * Save a fundraiser.
     *
     * @param fundraiserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FundraiserDTO save(FundraiserDTO fundraiserDTO) {
        log.debug("Request to save Fundraiser : {}", fundraiserDTO);
        Fundraiser fundraiser = fundraiserMapper.toEntity(fundraiserDTO);
        fundraiser = fundraiserRepository.save(fundraiser);
        return fundraiserMapper.toDto(fundraiser);
    }

    /**
     * Get all the fundraisers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<FundraiserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fundraisers");
        return fundraiserRepository.findAll(pageable)
            .map(fundraiserMapper::toDto);
    }


    /**
     * Get one fundraiser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<FundraiserDTO> findOne(String id) {
        log.debug("Request to get Fundraiser : {}", id);
        return fundraiserRepository.findById(id)
            .map(fundraiserMapper::toDto);
    }

    /**
     * Delete the fundraiser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Fundraiser : {}", id);
        fundraiserRepository.deleteById(id);
    }
}
