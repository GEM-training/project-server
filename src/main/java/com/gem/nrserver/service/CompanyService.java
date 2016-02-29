package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.CompanyDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by qsoft on 2/29/16.
 */
public interface CompanyService extends AbstractService<CompanyDTO, Long> {
    Page<StoreDTO> listStores(Long companyId, Pageable pageable) throws CompanyNotFoundException;

}
