package com.example.service.impl;

import com.example.dao.StudentMapper;
import com.example.exception.DaoRuntimeException;
import com.example.exception.ExceptionMatches;
import com.example.model.Clazz;
import com.example.model.Student;
import com.example.service.ClazzService;
import com.example.service.StudentService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mybatisConfig.xml","classpath:mybatisContext.xml"})
public class StudentServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @InjectMocks
    //@Autowired
    private StudentServiceImpl studentService;

    private static Student student;

    @Spy
    private StudentMapper studentMapper;

    @Mock
    //@Autowired
    private ClazzService clazzService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void setUp(){
        student = new Student();
        student.setNo("SX001");
        student.setName("张三");
        student.setAge(11);
        student.setSex(1);
        student.setGradeId(1L);
        student.setClazzId(1L);
    }

    @Before
    public void mockInit() {
        MockitoAnnotations.openMocks(this);
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

    @Test
    public void shouldReturnTeacherName() {
        // 初始化班级数据
        Clazz clazz = new Clazz();
        clazz.setClazzNo("0302");
        clazz.setClazzName("三年级二班");
        clazz.setTeacherName("张国栋");
        clazzService.save(clazz);

        // 初始化学生数据
        clazz = clazzService.selectByClassNo("0302");
        Student tom = new Student();
        tom.setNo("SX001");
        tom.setName("tom");
        tom.setAge(11);
        tom.setSex(1);
        tom.setGradeId(1L);
        tom.setClazzId(clazz.getClazzId());
        studentService.save(tom);

        // 根据学生学号查询所在班级教师名称
        String teacherName = studentService.selectTeacherName(tom.getNo());
        Assert.assertEquals("张国栋", teacherName);
    }

    @Test
    public void shouldReturnTeacherNameMock() {
        // 初始化班级数据
        Clazz clazz = new Clazz();
        clazz.setClazzNo("0302");
        clazz.setClazzName("三年级二班");
        clazz.setTeacherName("张国栋");
        //clazzService.save(clazz);
        clazz.setClazzId(123L);

        // 初始化学生数据
        //clazz = clazzService.selectByClassNo("0302");
        Student tom = new Student();
        tom.setNo("SX001");
        tom.setName("tom");
        tom.setAge(11);
        tom.setSex(1);
        tom.setGradeId(1L);
        tom.setClazzId(clazz.getClazzId());
        studentService.save(tom);

        Mockito.when(clazzService.selectByClassId(Mockito.any(Long.class))).thenReturn(clazz);

        // 根据学生学号查询所在班级教师名称
        String teacherName = studentService.selectTeacherName(tom.getNo());
        Assert.assertEquals("张国栋", teacherName);
    }
}
