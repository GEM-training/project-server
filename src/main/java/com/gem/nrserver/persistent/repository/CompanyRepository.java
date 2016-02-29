package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Company;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by qsoft on 2/29/16.
 */
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>, QueryDslPredicateExecutor<Company> {
}
