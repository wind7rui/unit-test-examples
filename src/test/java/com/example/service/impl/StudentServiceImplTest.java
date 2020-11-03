package com.example.service.impl;

import com.example.model.Student;
import com.example.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatisConfig.xml","classpath:mybatisContext.xml"})
public class StudentServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void shouldExecuteSuccess() {
        Student student = new Student();
        student.setNumber("SX001");
        student.setName("张三");
        student.setAge(11);
        student.setSex(1);
        student.setGrade(1);
        student.setClazz(2);
        studentService.save(student);

        Student s = studentService.selectByNumber("SX001");
        Assert.assertEquals(s.getName(), "张三");

        student.setName("李四");
        studentService.update(student);
        s = studentService.selectByNumber("SX001");
        Assert.assertEquals(s.getName(), "李四");

        studentService.delete(s.getId());
        s = studentService.selectByNumber("SX001");
        Assert.assertNull(s);
    }
}
