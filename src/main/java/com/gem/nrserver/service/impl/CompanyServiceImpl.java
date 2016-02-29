package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.Company;
import com.gem.nrserver.persistent.model.QCompany;
import com.gem.nrserver.persistent.repository.CompanyRepository;
import com.gem.nrserver.persistent.repository.StoreRepository;
import com.gem.nrserver.service.CompanyService;
import com.gem.nrserver.service.dto.CompanyDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.exception.CompanyNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mysema.query.types.Predicate;

/**
 * Created by qsoft on 2/29/16.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Long count() {
        return companyRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return companyRepository.exists(id);
    }

    @Override
    public void create(CompanyDTO dto) {
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        companyRepository.save(company);
    }

    @Override
    public void update(CompanyDTO dto) throws CompanyNotFoundException {

    }

    @Override
    public CompanyDTO findOne(Long id) throws Exception {
        Company company = companyRepository.findOne(id);
        if(company == null) throw new CompanyNotFoundException("could not find company" + id);
        CompanyDTO dto = new CompanyDTO();
        BeanUtils.copyProperties(company, dto);
        return dto;
    }

    @Override
    public Page<CompanyDTO> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable).map( source -> {
            CompanyDTO dto = new CompanyDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
                }
        );
    }

    @Override public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    public Page<StoreDTO> listStores(Long companyId, Pageable pageable) throws CompanyNotFoundException {
        if(!companyRepository.exists(companyId))
            throw new CompanyNotFoundException("could not find company" +companyId);
        Predicate isCompany = QCompany.company.stores
    }
 }

}
