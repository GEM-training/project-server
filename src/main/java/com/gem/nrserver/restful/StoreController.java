package com.gem.nrserver.restful;

import com.gem.nrserver.service.StoreService;
import com.gem.nrserver.service.dto.Product;
import com.gem.nrserver.service.dto.Store;
import com.gem.nrserver.service.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<Store> list(Pageable pageable) {
        return storeService.findAll(pageable);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody Store store) throws Exception {
        storeService.save(store);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@RequestBody Store store) throws Exception {
        storeService.update(store);
    }

    @RequestMapping(value = "", method=RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") long id) throws Exception {
        storeService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Store getById(@PathVariable(value = "id") long id) throws Exception {
        return storeService.findOne(id);
    }

    @RequestMapping(value = "/{id}/staff", method = RequestMethod.GET, produces = "application/json")
    public Page<User> listStaffs(@PathVariable(value = "id") Long storeId, Pageable pageable) throws Exception {
        return storeService.listStaffs(storeId, pageable);
    }

    @RequestMapping(value = "/{id}/staff", method = RequestMethod.POST)
    public void assignStaff(@RequestParam(name = "userId") String userId,
                            @PathVariable(value = "id") Long storeId) throws Exception {
        storeService.assignStaff(storeId, userId);
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.GET, produces = "application/json")
    public List<Product> listProducts(@PathVariable(value = "id") Long storeId, Pageable pageable) throws Exception {
        return storeService.listProducts(storeId);
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.POST)
    public void assignProduct(@RequestParam(name = "productId") Long productId,
                              @PathVariable(value = "id") Long storeId) throws Exception {
        storeService.assignProduct(storeId, productId);
    }

    @RequestMapping(value = "/{id}/customer", method = RequestMethod.GET, produces = "application/json")
    public Page<User> listCustomers(@PathVariable(value = "id") Long storeId, Pageable pageable) throws Exception {
        return storeService.listCustomers(storeId, pageable);
    }

}
