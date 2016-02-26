package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.StoreRepository;
import com.gem.nrserver.service.StoreService;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.util.ModelAndDTOMapper;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.exception.ResourceNotFoundException;
import com.gem.nrserver.service.exception.StoreNotFoundException;
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
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Page<UserDTO> listStaffs(Long storeId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductDTO> listProducts(Long storeId, Pageable pageable) {
        return productRepository.listProductsInStore(storeId, pageable).map(ModelAndDTOMapper::productModelToDTO);
    }

    @Override
    public Page<UserDTO> listCustomers(Long storeId, Pageable pageable) {
        return null;
    }

    @Override
    public void assignStaff(Long storeId, String userId) {

    }

    @Override
    public void assignProduct(Long storeId, Long productId) {

    }

    @Override
    public Long count() {
        return storeRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return storeRepository.exists(id);
    }

    @Override
    public Long save(StoreDTO dto) {
        return storeRepository.save(ModelAndDTOMapper.storeDTOtoModel(dto)).getId();
    }

    @Override
    public void update(StoreDTO dto) {
        com.gem.nrserver.persistent.model.Store store = storeRepository.findOne(dto.getId());
        store.setName(dto.getName());
        store.setDescription(dto.getDescription());
        storeRepository.save(store);
    }

    @Override
    public StoreDTO findOne(Long id) throws ResourceNotFoundException {
        com.gem.nrserver.persistent.model.Store store = storeRepository.findOne(id);
        if(store == null) throw new StoreNotFoundException();
        return ModelAndDTOMapper.storeModeltoDTO(store);
    }


    @Override
    public Page<StoreDTO> findAll(Pageable pageable) {
        Page<com.gem.nrserver.persistent.model.Store> stores = storeRepository.findAll(pageable);
        return stores.map(ModelAndDTOMapper::storeModeltoDTO);
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }
}
