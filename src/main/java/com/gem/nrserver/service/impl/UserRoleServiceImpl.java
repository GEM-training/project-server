package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.repository.UserRoleRepository;
import com.gem.nrserver.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by quanda on 19/02/2016.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<String> findByUsername(String username) {
        return userRoleRepository.findByUsername(username);
    }
}
