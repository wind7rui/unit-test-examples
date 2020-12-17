package com.example.service;

import com.example.model.Clazz;

public interface ClazzService {

    int save(Clazz clazz);

    int update(Clazz clazz);

    Clazz selectByClassId(Long clazzId);

    long countByClazzId(Long clazzId);
}
