package net.javadog.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.blog.common.enums.CommonStatusEnum;
import net.javadog.blog.common.enums.ServiceErrorEnum;
import net.javadog.blog.common.exception.ServiceException;
import net.javadog.blog.common.util.NickNameUtil;
import net.javadog.blog.dto.request.PasswordRequest;
import net.javadog.blog.dto.request.UserRequest;
import net.javadog.blog.dto.response.UserResponse;
import net.javadog.blog.entity.User;
import net.javadog.blog.mapper.UserMapper;
import net.javadog.blog.service.UserService;
import net.javadog.blog.shiro.util.JwtUtil;
import net.javadog.blog.shiro.util.SubjectUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(UserRequest userRequest) {
        User user = new User();
        BeanUtil.copyProperties(userRequest, user);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername()).eq(User::getStatus,CommonStatusEnum.YES.getCode());
        User existUser = this.getOne(queryWrapper);
        if(ObjectUtil.isNotNull(existUser)){
            throw new ServiceException(ServiceErrorEnum.USERNAME_EXIST_ERROR);
        }
        // 头像
        String avatar = "http://api.multiavatar.com/" + (int) (Math.random() * 100) + ".png";
        user.setAvatar(avatar);
        // 密码加密
        String password = userRequest.getPassword();
        user.setPassword(SecureUtil.md5(password));
        user.setNickname(NickNameUtil.getNickName());
        user.setStatus(CommonStatusEnum.YES.getCode());
        user.setCreateTime(new Date());
        this.save(user);
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username)
                .eq(User::getPassword, SecureUtil.md5(password))
                .eq(User::getStatus,CommonStatusEnum.YES.getCode());
        User user = this.getOne(queryWrapper);
        if(ObjectUtil.isNull(user)){
            throw new ServiceException(ServiceErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        userResponse.setAccessToken(JwtUtil.createToken(user.getId()));
        return userResponse;
    }

    @Override
    public UserResponse detail() {
        Long userId = SubjectUtil.getUserId();
        User user = this.getById(userId);
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        return userResponse;
    }

    @Override
    public void update(UserRequest request) {
        User user = new User();
        BeanUtil.copyProperties(request, user);
        this.saveOrUpdate(user);
    }

    @Override
    public void updatePassword(PasswordRequest request) {
        Long id = request.getId();
        User user = this.getById(id);
        String originalPassword = request.getOriginalPassword();
        String newPassword = request.getNewPassword();
        if(!StrUtil.equals(user.getPassword(), SecureUtil.md5(originalPassword))){
            throw new ServiceException(ServiceErrorEnum.ORIGINAL_PASSWORD_ERROR);
        }
        user.setPassword(SecureUtil.md5(newPassword));
        this.saveOrUpdate(user);
    }
}




