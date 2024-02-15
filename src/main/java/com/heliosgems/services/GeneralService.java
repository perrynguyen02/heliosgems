package com.heliosgems.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public interface GeneralService<T, D> {
    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    List<T> findAll();

    T findById(UUID uuid);

    T create(D d);

    T update(UUID uuid, D d);

    void delete(UUID uuid);

    default T mapToEntity(D d, Type type) {
        return modelMapper.map(d, type);
    }

    default D mapToDto(T t, Type type) {
        return modelMapper.map(t, type);
    }
}