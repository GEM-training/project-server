package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.QInvoice;
import com.gem.nrserver.persistent.model.QUser;
import com.gem.nrserver.persistent.model.Store;
import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.StoreRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.StoreService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.ResourceNotFoundException;
import com.gem.nrserver.service.exception.StoreNotFoundException;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<UserDTO> listStaffs(Long storeId, Pageable pageable) {
        BooleanExpression isStaff = QUser.user.store.id.eq(storeId);
        Page<User> staffs = userRepository.findAll(isStaff, pageable);
        return staffs.map(source -> {
            UserDTO dto = new UserDTO();
            dto.setUsername(source.getUsername());
            return dto;
        });
    }

    @Override
    public Page<ProductDTO> listProducts(Long storeId, Pageable pageable) {
        return productRepository.listProductsInStore(storeId, pageable).map(source -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public Page<UserDTO> listCustomers(Long storeId, Pageable pageable) {
       Predicate isCustomer = QUser.user.in(new JPASubQuery().distinct().
               from(QInvoice.invoice).where(QInvoice.invoice.store.id.eq(storeId)).list(QInvoice.invoice.customer));
        return userRepository.findAll(isCustomer, pageable).map(source -> {
            UserDTO dto = new UserDTO();
            dto.setUsername(source.getUsername());
            return dto;
        });
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
    public void create(StoreDTO dto) {
        Store store = new Store();
        BeanUtils.copyProperties(dto, store);
        storeRepository.save(store);
    }

    @Override
    public void update(StoreDTO dto) {
        Store store = storeRepository.findOne(dto.getId());
        store.setName(dto.getName());
        store.setDescription(dto.getDescription());
        storeRepository.save(store);
    }

    @Override
    public StoreDTO findOne(Long id) throws ResourceNotFoundException {
        Store store = storeRepository.findOne(id);
        if(store == null) throw new StoreNotFoundException();
        StoreDTO dto = new StoreDTO();
        BeanUtils.copyProperties(store, dto);
        return dto;
    }


    @Override
    public Page<StoreDTO> findAll(Pageable pageable) {
        Page<Store> stores = storeRepository.findAll(pageable);
        return stores.map(source -> {
            StoreDTO dto = new StoreDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }
}
