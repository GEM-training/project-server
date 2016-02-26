package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.model.UserRole;
import com.gem.nrserver.persistent.repository.ProductRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.util.ModelAndDTOMapper;
import org.apache.log4j.Logger;
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

/**
 * Created by kimtung on 2/18/16.
 */
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
        return productRepository.listProductPurchasedByUser(userId, pageable).map(ModelAndDTOMapper::productModelToDTO);
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
    public String save(UserDTO dto) throws Exception {
        if(!userRepository.isUsernameAvailable(dto.getUsername()))
            throw new IllegalArgumentException("username is not available");
        return userRepository.save(ModelAndDTOMapper.userDTOtoModel(dto)).getUsername();
    }

    @Override
    public void update(UserDTO dto) throws Exception {

    }

    @Override
    public UserDTO findOne(String s) throws Exception {
        return ModelAndDTOMapper.userModelToDTO(userRepository.findOne(s));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(ModelAndDTOMapper::userModelToDTO);
    }

    @Override
    public void delete(String s) throws Exception {
        userRepository.delete(s);
    }
}
