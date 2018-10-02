package com.xq.entity;

import java.util.List;

public class Student {

    private Integer id;
    private String stuName;
    private Integer classId;

    private Class aClass;

    private List<Tag> tagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", classId=" + classId +
                ", aClass=" + aClass +
                ", tagList=" + tagList +
                '}';
    }
}
