package com.example.model;

/**
 * 班级信息
 */
public class Clazz {

    /**
     * 班级编号
     */
    private Long clazzId;

    /**
     * 班级编号
     */
    private String clazzNo;
    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 老师姓名
     */
    private String teacherName;

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzId=" + clazzId +
                ", clazzNo='" + clazzNo + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
