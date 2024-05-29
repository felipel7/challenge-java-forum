package com.challenge.forum.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;

public class CopyUtils {

    public static void copyNonNullProperties(
        Object source,
        Object target
    ) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    public static String[] getNullProperties(Object objSource) {
        final BeanWrapper source = new BeanWrapperImpl(objSource);
        var propertyDescriptors = source.getPropertyDescriptors();
        var nullProperties = new HashSet<>();

        for (PropertyDescriptor pd : propertyDescriptors) {
            var value = source.getPropertyValue(pd.getName());

            if (value == null) nullProperties.add(pd.getName());
        }

        var result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }
}
