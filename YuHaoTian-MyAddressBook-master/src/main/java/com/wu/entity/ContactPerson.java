package com.wu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ContactPerson)实体类
 *
 * @author wuyou
 * @since 2021-10-19 09:32:06
 */
@Data
public class ContactPerson implements Serializable {
    private static final long serialVersionUID = 235429554015588623L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 住址
     */
    private String address;
}

