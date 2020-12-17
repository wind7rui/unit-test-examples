package com.example.dao;

import com.example.model.Clazz;

public interface ClazzMapper {

    int save(Clazz clazz);

    int update(Clazz clazz);

    Clazz selectByClassId(Long clazzId);

    long countByClazzId(Long clazzId);
}
