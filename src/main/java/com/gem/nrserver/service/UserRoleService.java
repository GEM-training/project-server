package com.gem.nrserver.service;

import java.util.List;

/**
 * Created by quanda on 19/02/2016.
 */
public interface UserRoleService {

    List<String> findByUsername(String username);

}
