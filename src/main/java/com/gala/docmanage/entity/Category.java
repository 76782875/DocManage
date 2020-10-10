package com.gala.docmanage.entity;

import com.gala.docmanage.util.BeanUtils;

import java.sql.Timestamp;

/**
 * 分类表
 */
public class Category {

    private int id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目号
     */
    private int code;

    private Timestamp createTime;

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name,int code ,Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return BeanUtils.toPrettyJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
