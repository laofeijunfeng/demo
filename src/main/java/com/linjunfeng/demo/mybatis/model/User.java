package com.linjunfeng.demo.mybatis.model;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private String name;

    private Integer age;

    private Long tmCreate;

    private Long tmUpdate;

    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getTmCreate() {
        return tmCreate;
    }

    public void setTmCreate(Long tmCreate) {
        this.tmCreate = tmCreate;
    }

    public Long getTmUpdate() {
        return tmUpdate;
    }

    public void setTmUpdate(Long tmUpdate) {
        this.tmUpdate = tmUpdate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}