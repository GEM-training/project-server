package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.repository.UserRoleRepository;
import com.gem.nrserver.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
}
