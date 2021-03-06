package com.gem.nrserver.restful;

import com.gem.nrserver.service.StoreService;
import com.gem.nrserver.service.dto.InvoiceDTO;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<StoreDTO> list(Pageable pageable) {
        return storeService.findAll(pageable);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody StoreDTO storeDTO) throws Exception {
        storeService.create(storeDTO);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@RequestBody StoreDTO storeDTO) throws Exception {
        storeService.update(storeDTO);
    }

    @RequestMapping(value = "", method=RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") long id) throws Exception {
        storeService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public StoreDTO getById(@PathVariable(value = "id") long id) throws Exception {
        return storeService.findOne(id);
    }

    @RequestMapping(value = "/{id}/staff", method = RequestMethod.GET, produces = "application/json")
    public Page<UserDTO> listStaffs(@PathVariable(value = "id") Long storeId, Pageable pageable) throws Exception {
        return storeService.listStaffs(storeId, pageable);
    }

    @RequestMapping(value = "/{id}/staff", method = RequestMethod.POST)
    public void assignStaff(@RequestParam(name = "userId") String userId,
                            @PathVariable(value = "id") Long storeId) throws Exception {
        storeService.assignStaff(storeId, userId);
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDTO> listProducts(@PathVariable(value = "id") Long storeId,
                                         @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-mm-dd") Date from,
                                         @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-mm-dd") Date to,
                                         Pageable pageable) throws Exception {
        if(from == null) {
            return storeService.listProducts(storeId, pageable);
        } else {
            if(to == null) {
                to = new Date();
            }
            return storeService.listProducts(storeId, from, to, pageable);
        }
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.POST)
    public void assignProduct(@RequestParam(name = "productId") Long productId,
                              @PathVariable(value = "id") Long storeId) throws Exception {
        storeService.assignProduct(storeId, productId);
    }

    @RequestMapping(value = "/{id}/customer", method = RequestMethod.GET, produces = "application/json")
    public Page<UserDTO> listCustomers(@PathVariable(value = "id") Long storeId,
                                       @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-mm-dd") Date from,
                                       @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-mm-dd") Date to,
                                       Pageable pageable) throws Exception {
        if(from == null) {
            return storeService.listCustomers(storeId, pageable);
        } else {
            if(to == null) {
                to = new Date();
            }
            return storeService.listCustomers(storeId, from, to, pageable);
        }
    }

    @RequestMapping(value = "/{id}/invoice", method = RequestMethod.GET, produces = "application/json")
    public Page<InvoiceDTO> listInvoices(@PathVariable(value = "id") Long storeId,
                                         @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-mm-dd") Date from,
                                         @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-mm-dd") Date to,
                                         Pageable pageable) throws Exception {
        if(from == null) {
            return storeService.listInvoices(storeId, pageable);
        } else {
            if(to == null) {
                to = new Date();
            }
            return storeService.listInvoices(storeId, from, to, pageable);
        }
    }

}
