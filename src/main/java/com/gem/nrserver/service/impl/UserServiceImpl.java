package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.*;
import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.UserNotFoundException;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("service_user")
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.isUsernameAvailable(username);
    }

    @Override
    public Page<ProductDTO> listPurchasedProduct(String userId, Pageable pageable) throws Exception{
        Predicate predicate = QProduct.product
                .in(new JPASubQuery().distinct()
                        .from(QInvoice.invoice,QInvoiceDetail.invoiceDetail)
                        .where(QInvoice.invoice.id.eq(QInvoiceDetail.invoiceDetail.invoice.id)
                                .and(QInvoice.invoice.customer.username.eq(userId)))
                        .list(QInvoiceDetail.invoiceDetail.product));
        return productRepository.findAll(predicate, pageable).map(source -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        List<GrantedAuthority> authorities = buildUserAuthorities(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> authorities = userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
        return new ArrayList<>(authorities);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public boolean exists(String s) {
        return userRepository.exists(s);
    }

    @Override
    public void create(UserDTO dto) throws Exception {
        if(!userRepository.isUsernameAvailable(dto.getUsername()))
            throw new IllegalArgumentException("username is not available");
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO dto) throws Exception {

    }

    @Override
    public UserDTO findOne(String id) throws Exception {
        User user = userRepository.findOne(id);
        if(user == null) throw new UserNotFoundException();
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(source -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public void delete(String s) throws Exception {
        userRepository.delete(s);
    }
}
