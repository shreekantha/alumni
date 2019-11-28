package com.heraizen.dhi.alumni.service.impl;

import com.heraizen.dhi.alumni.service.ReqOrClaimSubjectService;
import com.heraizen.dhi.alumni.domain.ReqOrClaimSubject;
import com.heraizen.dhi.alumni.repository.ReqOrClaimSubjectRepository;
import com.heraizen.dhi.alumni.service.dto.ReqOrClaimSubjectDTO;
import com.heraizen.dhi.alumni.service.mapper.ReqOrClaimSubjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReqOrClaimSubject}.
 */
@Service
public class ReqOrClaimSubjectServiceImpl implements ReqOrClaimSubjectService {

    private final Logger log = LoggerFactory.getLogger(ReqOrClaimSubjectServiceImpl.class);

    private final ReqOrClaimSubjectRepository reqOrClaimSubjectRepository;

    private final ReqOrClaimSubjectMapper reqOrClaimSubjectMapper;

    public ReqOrClaimSubjectServiceImpl(ReqOrClaimSubjectRepository reqOrClaimSubjectRepository, ReqOrClaimSubjectMapper reqOrClaimSubjectMapper) {
        this.reqOrClaimSubjectRepository = reqOrClaimSubjectRepository;
        this.reqOrClaimSubjectMapper = reqOrClaimSubjectMapper;
    }

    /**
     * Save a reqOrClaimSubject.
     *
     * @param reqOrClaimSubjectDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReqOrClaimSubjectDTO save(ReqOrClaimSubjectDTO reqOrClaimSubjectDTO) {
        log.debug("Request to save ReqOrClaimSubject : {}", reqOrClaimSubjectDTO);
        ReqOrClaimSubject reqOrClaimSubject = reqOrClaimSubjectMapper.toEntity(reqOrClaimSubjectDTO);
        reqOrClaimSubject = reqOrClaimSubjectRepository.save(reqOrClaimSubject);
        return reqOrClaimSubjectMapper.toDto(reqOrClaimSubject);
    }

    /**
     * Get all the reqOrClaimSubjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ReqOrClaimSubjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReqOrClaimSubjects");
        return reqOrClaimSubjectRepository.findAll(pageable)
            .map(reqOrClaimSubjectMapper::toDto);
    }


    /**
     * Get one reqOrClaimSubject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ReqOrClaimSubjectDTO> findOne(String id) {
        log.debug("Request to get ReqOrClaimSubject : {}", id);
        return reqOrClaimSubjectRepository.findById(id)
            .map(reqOrClaimSubjectMapper::toDto);
    }

    /**
     * Delete the reqOrClaimSubject by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ReqOrClaimSubject : {}", id);
        reqOrClaimSubjectRepository.deleteById(id);
    }
}
