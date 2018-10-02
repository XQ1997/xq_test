package com.xq.entity;

public class Class {

    private Integer id;
    private String className;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
