package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.UserRequest;
import net.javadog.blog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "登录控制器")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping
    public ResponseData login(@RequestBody UserRequest userRequest){
        return ResponseData.success(userService.login(userRequest));
    }

}
