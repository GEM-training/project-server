package com.gem.nrserver.restful;

import com.gem.nrserver.service.InvoiceService;
import com.gem.nrserver.service.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<InvoiceDTO> list(Pageable pageable) {
        return invoiceService.findAll(pageable);
    }

}
