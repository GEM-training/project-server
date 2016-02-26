package com.gem.nrserver.service.util;

import com.gem.nrserver.persistent.model.Store;
import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;

/**
 * Created by kimtung on 2/24/16.
 */
public class ModelAndDTOMapper {

    public static Store storeDTOtoModel(StoreDTO dto) {
        com.gem.nrserver.persistent.model.Store model = new com.gem.nrserver.persistent.model.Store();
        model.setName(dto.getName());
        model.setAddress(dto.getAddress());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static StoreDTO storeModeltoDTO(com.gem.nrserver.persistent.model.Store model) {
        StoreDTO dto = new StoreDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setAddress(model.getAddress());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public static ProductDTO productModelToDTO(com.gem.nrserver.persistent.model.Product model) {
        ProductDTO dto = new ProductDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public static com.gem.nrserver.persistent.model.Product productDTOtoModel(ProductDTO dto) {
        com.gem.nrserver.persistent.model.Product model = new com.gem.nrserver.persistent.model.Product();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static UserDTO userModelToDTO(User model) {
        UserDTO dto = new UserDTO();
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        return dto;
    }

    public static User userDTOtoModel(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }


}
