package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.Product;
import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.service.ProductService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    

    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return productRepository.exists(id);
    }

    @Override
    public void create(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        productRepository.save(product);
    }

    @Override
    public void update(ProductDTO dto) throws ProductNotFoundException {
        Product product = productRepository.findOne(dto.getId());
        if(product == null)
            throw new ProductNotFoundException("could not find product " + dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        productRepository.save(product);
    }

    @Override
    public ProductDTO findOne(Long id) throws ProductNotFoundException {
        Product product = productRepository.findOne(id);
        if(product == null) throw new ProductNotFoundException("could not find product " + id);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(source -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
