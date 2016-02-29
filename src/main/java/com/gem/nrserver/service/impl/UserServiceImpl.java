package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.*;
import com.gem.nrserver.persistent.repository.InvoiceRepository;
import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.InvoiceDTO;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.UserNotFoundException;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("service_user")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.isUsernameAvailable(username);
    }

    @Override
    public Page<ProductDTO> listPurchasedProducts(String userId, Pageable pageable) throws UserNotFoundException {
        if(!userRepository.exists(userId))
            throw new UserNotFoundException("could not find user " + userId);
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
    public Page<InvoiceDTO> listInvoices(String userId, Pageable pageable) throws UserNotFoundException {
        if(!userRepository.exists(userId))
            throw new UserNotFoundException("could not find user " + userId);
        Predicate isInvoice = QInvoice.invoice.customer.username.eq(userId);
        return invoiceRepository.findAll(isInvoice, pageable).map(source -> {
            InvoiceDTO dto = new InvoiceDTO();
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
    public void create(UserDTO dto) {
        if(!userRepository.isUsernameAvailable(dto.getUsername()))
            throw new IllegalArgumentException("username is not available");
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO dto) {

    }

    @Override
    public UserDTO findOne(String id) throws Exception {
        if(!userRepository.exists(id))
            throw new UserNotFoundException("could not find user " + id);
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(userRepository.findOne(id), dto);
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
    public void delete(String s) {
        userRepository.delete(s);
    }
}
