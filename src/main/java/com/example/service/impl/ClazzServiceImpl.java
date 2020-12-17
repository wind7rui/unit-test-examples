package com.example.service.impl;

import com.example.dao.ClazzMapper;
import com.example.model.Clazz;
import com.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    public int save(Clazz clazz) {
        return clazzMapper.save(clazz);
    }

    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    public Clazz selectByClassId(Long clazzId) {
        return clazzMapper.selectByClassId(clazzId);
    }

    public long countByClazzId(Long clazzId) {
        return clazzMapper.countByClazzId(clazzId);
    }
}
