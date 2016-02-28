package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Invoice;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long>, QueryDslPredicateExecutor<Invoice> {
}
