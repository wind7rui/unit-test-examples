package com.example.service;

import com.example.model.Student;

public interface StudentService {
    int save(Student student);

    Student selectByNo(String no);

    int update(Student student);

    int delete(long id);

    int saveAndUpdate(Student student);

    int saveThenUpdate(Student student);

    /**
     * 根据学号统计所在班级的学生总数
     * @param no
     * @return
     */
    long countClazzStudent(String no);
}
