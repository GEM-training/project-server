package com.gem.nrserver.service.util;

import com.gem.nrserver.service.dto.Product;
import com.gem.nrserver.service.dto.Store;

/**
 * Created by kimtung on 2/24/16.
 */
public class ModelDtoMapper {

    public static com.gem.nrserver.persistent.model.Store storeDTOtoModel(Store dto) {
        com.gem.nrserver.persistent.model.Store model = new com.gem.nrserver.persistent.model.Store();
        model.setName(dto.getName());
        model.setAddress(dto.getAddress());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static Store storeModeltoDTO(com.gem.nrserver.persistent.model.Store model) {
        Store dto = new Store();
        dto.setName(model.getName());
        dto.setAddress(model.getAddress());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public static Product productModelToDTO(com.gem.nrserver.persistent.model.Product model) {
        Product dto = new Product();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public static com.gem.nrserver.persistent.model.Product productDTOtoModel(Product dto) {
        com.gem.nrserver.persistent.model.Product model = new com.gem.nrserver.persistent.model.Product();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        return model;
    }

}
