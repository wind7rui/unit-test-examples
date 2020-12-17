package com.example.service.impl;

import com.example.exception.DaoRuntimeException;
import com.example.exception.ExceptionMatches;
import com.example.model.Student;
import com.example.service.StudentService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatisConfig.xml","classpath:mybatisContext.xml"})
public class StudentServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StudentService studentService;

    private static Student student;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void setUp(){
        System.out.println("init");
        student = new Student();
        student.setNo("SX001");
        student.setName("张三");
        student.setAge(11);
        student.setSex(1);
        student.setGradeId(1L);
        student.setClazzId(2L);
    }

    @Test
    public void shouldExecuteSuccessWhenCrud() {
       /* Student student = new Student();
        student.setNumber("SX001");
        student.setName("张三");
        student.setAge(11);
        student.setSex(1);
        student.setGrade(1);
        student.setClazz(2);*/
        studentService.save(student);

        Student s = studentService.selectByNo("SX001");
        Assert.assertEquals(s.getName(), "张三");

        student.setName("李四");
        studentService.update(student);
        s = studentService.selectByNo("SX001");
        Assert.assertEquals(s.getName(), "李四");

        studentService.delete(s.getId());
        s = studentService.selectByNo("SX001");
        Assert.assertNull(s);
    }

    @Test
    public void shouldRollbackWhenThrowRuntimeException() {
        /*Student student = new Student();
        student.setNumber("SX001");
        student.setName("张三");
        student.setAge(11);
        student.setSex(1);
        student.setGrade(1);
        student.setClazz(2);*/

        try {
            studentService.saveAndUpdate(student);
        } catch (Exception e) {
            // doSomething
            System.out.println(e.getMessage());
        }

        Student s = studentService.selectByNo("SX001");
        Assert.assertNull(s);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowNumberFormatExceptionWhenStudentNumberError() {
        studentService.saveAndUpdate(student);
    }

    @Test
    public void shouldThrowDaoRuntimeExceptionWhenStudentNumberError() {
        // 校验是否是预期异常类型
        expectedException.expect(DaoRuntimeException.class);
        // 校验抛出的异常是否和自定义的异常校验器相匹配
        expectedException.expect(new ExceptionMatches("10010001", "Student Number Error"));

        studentService.saveThenUpdate(student);
    }
}
