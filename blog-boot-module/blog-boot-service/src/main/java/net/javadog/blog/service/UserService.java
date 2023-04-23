package net.javadog.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.blog.dto.request.PasswordRequest;
import net.javadog.blog.dto.request.UserRequest;
import net.javadog.blog.dto.response.UserResponse;
import net.javadog.blog.entity.User;

/**
 * 用户接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface UserService extends IService<User> {

    void register(UserRequest request);

    UserResponse login(UserRequest request);

    UserResponse detail();

    void update(UserRequest request);

    void updatePassword(PasswordRequest request);
}
