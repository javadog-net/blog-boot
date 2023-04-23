package net.javadog.blog.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户request
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/
@Data
public class UserRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别(0-未知；1-男；2-女)
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 简介
     */
    private String intro;

}