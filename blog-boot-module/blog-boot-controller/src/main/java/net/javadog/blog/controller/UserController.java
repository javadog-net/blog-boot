package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.PasswordRequest;
import net.javadog.blog.dto.request.UserRequest;
import net.javadog.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户-详情", notes = "用户-详情")
    @GetMapping
    public ResponseData detail(){
        return ResponseData.success( userService.detail());
    }

    @ApiOperation(value = "用户-更新", notes = "用户-更新")
    @PutMapping
    public ResponseData update(@RequestBody UserRequest request){
        userService.update(request);
        return ResponseData.success();
    }

    @ApiOperation(value = "用户密码-更新", notes = "用户密码-更新")
    @PutMapping("password")
    public ResponseData updatePassword(@RequestBody PasswordRequest request){
        userService.updatePassword(request);
        return ResponseData.success();
    }

}
