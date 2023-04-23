package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.UserRequest;
import net.javadog.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 注册控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "注册控制器")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping
    public ResponseData register(@RequestBody UserRequest userRequest){
        userService.register(userRequest);
        return ResponseData.success();
    }

}
