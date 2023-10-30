package com.wu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author wuyou
 * @since 2021-10-19 09:31:43
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -94636525817631348L;
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 账号
     */
    private String number;
    /**
     * 密码
     */
    private String password;
}

