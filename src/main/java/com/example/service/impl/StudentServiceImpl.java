package com.example.service.impl;

import com.example.dao.StudentMapper;
import com.example.exception.DaoRuntimeException;
import com.example.model.Clazz;
import com.example.model.Student;
import com.example.service.ClazzService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzService clazzService;

    public int save(Student student) {
        return studentMapper.save(student);
    }

    public Student selectByNo(String no) {
        return studentMapper.selectByNo(no);
    }

    public int update(Student student) {
        return studentMapper.update(student);
    }

    public int delete(long id) {
        return studentMapper.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveAndUpdate(Student student) {
        save(student);

        // 抛java.lang.NumberFormatException
        Long.valueOf(student.getNo()) ;

        update(student);

        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveThenUpdate(Student student) {
        save(student);

        try {
            // 抛java.lang.NumberFormatException
            Long.valueOf(student.getNo()) ;
        } catch (NumberFormatException e) {
            throw new DaoRuntimeException("10010001", "Student Number Error" ,e);
        }

        update(student);

        return 1;
    }

    public String selectTeacherName(String no) {
        Student student = studentMapper.selectByNo(no);

        Clazz clazz = clazzService.selectByClassId(student.getClazzId());

        return null == clazz ? "none" : clazz.getTeacherName();
    }
}
