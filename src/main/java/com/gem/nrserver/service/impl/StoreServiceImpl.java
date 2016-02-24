package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.StoreRepository;
import com.gem.nrserver.service.StoreService;
import com.gem.nrserver.service.util.ModelDtoMapper;
import com.gem.nrserver.service.dto.Product;
import com.gem.nrserver.service.dto.Store;
import com.gem.nrserver.service.dto.User;
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
import java.util.Set;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<User> listStaffs(Long storeId) {
        return null;
    }

    @Override
    public Page<User> listStaffs(Long storeId, Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> listProducts(Long storeId) throws Exception {
        com.gem.nrserver.persistent.model.Store store = storeRepository.findOne(storeId);
        if(store == null) throw new StoreNotFoundException();
        Set<com.gem.nrserver.persistent.model.Product> productSet = store.getProducts();
        ArrayList<Product> products = new ArrayList<Product>();
        for (com.gem.nrserver.persistent.model.Product product: productSet) {
            products.add(ModelDtoMapper.productModelToDTO(product));
        }
        return products;
    }

    @Override
    public Page<User> listProducts(Long storeId, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> listCustomers(Long storeId) {
        return null;
    }

    @Override
    public Page<User> listCustomers(Long storeId, Pageable pageable) {
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
    public Long save(Store dto) {
        return storeRepository.save(ModelDtoMapper.storeDTOtoModel(dto)).getId();
    }

    @Override
    public void update(Store dto) {
        com.gem.nrserver.persistent.model.Store store = storeRepository.findOne(dto.getId());
        store.setName(dto.getName());
        store.setDescription(dto.getDescription());
        storeRepository.save(store);
    }

    @Override
    public Store findOne(Long id) throws ResourceNotFoundException {
        com.gem.nrserver.persistent.model.Store store = storeRepository.findOne(id);
        if(store == null) throw new StoreNotFoundException();
        return ModelDtoMapper.storeModeltoDTO(store);
    }

    @Override
    public List<Store> findAll() {
        Iterable<com.gem.nrserver.persistent.model.Store> stores = storeRepository.findAll();
        ArrayList<Store> storeDTOs = new ArrayList<Store>();
        for(com.gem.nrserver.persistent.model.Store store: stores){
            storeDTOs.add(ModelDtoMapper.storeModeltoDTO(store));
        }
        return storeDTOs;
    }

    @Override
    public Page<Store> findAll(Pageable pageable) {
        Page<com.gem.nrserver.persistent.model.Store> stores = storeRepository.findAll(pageable);
        return stores.map(new Converter<com.gem.nrserver.persistent.model.Store, Store>() {
            @Override
            public Store convert(com.gem.nrserver.persistent.model.Store source) {
                return ModelDtoMapper.storeModeltoDTO(source);
            }
        });
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }
}
