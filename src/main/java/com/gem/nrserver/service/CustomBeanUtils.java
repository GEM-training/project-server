package com.gem.nrserver.service;

import org.springframework.beans.BeanUtils;

/**
 * Created by kimtung on 2/26/16.
 */
public class CustomBeanUtils extends BeanUtils {

    public static Object copyPropterties(Object source, Class destClass) throws ClassNotFoundException, NoSuchMethodException {

            Object destObject = Class.forName(destClass.getName()).getConstructor();
            copyProperties(source, destObject);
            return destObject;
    }

}
