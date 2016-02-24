package com.gem.nrserver.restful;

import com.gem.nrserver.service.ProductService;
import com.gem.nrserver.service.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kimtung on 2/24/16.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<Product> list(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody Product product) throws Exception {
        productService.save(product);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@RequestBody Product product) throws Exception {
        productService.update(product);
    }

    @RequestMapping(value = "", method=RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") long id) throws Exception {
        productService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Product getById(@PathVariable(value = "id") long id) throws Exception {
        return productService.findOne(id);
    }
}
