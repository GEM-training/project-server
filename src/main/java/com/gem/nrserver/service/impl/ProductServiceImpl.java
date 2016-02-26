package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.service.ProductService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.exception.ProductNotFoundException;
import com.gem.nrserver.service.util.ModelAndDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public Long save(ProductDTO dto) {
        return productRepository.save(ModelAndDTOMapper.productDTOtoModel(dto)).getId();
    }

    @Override
    public void update(ProductDTO dto) {
        com.gem.nrserver.persistent.model.Product product = productRepository.findOne(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        productRepository.save(product);
    }

    @Override
    public ProductDTO findOne(Long id) throws ProductNotFoundException {
        com.gem.nrserver.persistent.model.Product product = productRepository.findOne(id);
        if(product == null) throw new ProductNotFoundException();
        return ModelAndDTOMapper.productModelToDTO(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        Iterable<com.gem.nrserver.persistent.model.Product> products = productRepository.findAll();
        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        for(com.gem.nrserver.persistent.model.Product product : products){
            productDTOs.add(ModelAndDTOMapper.productModelToDTO(product));
        }
        return productDTOs;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<com.gem.nrserver.persistent.model.Product> products = productRepository.findAll(pageable);
        return products.map(ModelAndDTOMapper::productModelToDTO);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
