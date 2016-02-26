package com.gem.nrserver.restful;

import com.gem.nrserver.service.ProductService;
import com.gem.nrserver.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDTO> list(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody ProductDTO productDTO) throws Exception {
        productService.create(productDTO);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@RequestBody ProductDTO productDTO) throws Exception {
        productService.update(productDTO);
    }

    @RequestMapping(value = "", method=RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") long id) throws Exception {
        productService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductDTO getById(@PathVariable(value = "id") long id) throws Exception {
        return productService.findOne(id);
    }
}
