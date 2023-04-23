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
public class PasswordRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 原密码
     */
    private String originalPassword;

    /**
     * 新密码
     */
    private String newPassword;

}