package com.example.dao;

import com.example.model.Student;

public interface StudentMapper {

    int save(Student student);

    Student selectByNumber(String number);

    int update(Student student);

    int delete(long id);
}
