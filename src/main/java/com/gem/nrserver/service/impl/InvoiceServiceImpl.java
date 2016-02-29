package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.Invoice;
import com.gem.nrserver.persistent.repository.InvoiceRepository;
import com.gem.nrserver.service.InvoiceService;
import com.gem.nrserver.service.dto.InvoiceDTO;
import com.gem.nrserver.service.exception.InvoiceNotFoundException;
import com.gem.nrserver.service.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Long count() {
        return invoiceRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return invoiceRepository.exists(id);
    }

    @Override
    public void create(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(dto, invoice);
        invoiceRepository.save(invoice);
    }

    @Override
    public void update(InvoiceDTO dto) throws ProductNotFoundException {

    }

    @Override
    public InvoiceDTO findOne(Long id) throws Exception {
        Invoice invoice = invoiceRepository.findOne(id);
        if(invoice == null) throw new InvoiceNotFoundException("could not find invoice " + id);
        InvoiceDTO dto = new InvoiceDTO();
        BeanUtils.copyProperties(invoice, dto);
        return dto;
    }

    @Override
    public Page<InvoiceDTO> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(source -> {
            InvoiceDTO dto = new InvoiceDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.delete(id);
    }
}
