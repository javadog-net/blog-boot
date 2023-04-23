package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.TagRequest;
import net.javadog.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 标签控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "标签控制器")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @ApiOperation(value = "标签-新增", notes = "标签-新增")
    @PostMapping
    public ResponseData save(@RequestBody TagRequest tagRequest){
        return ResponseData.success(tagService.save(tagRequest));
    }

    @ApiOperation(value = "标签-详情", notes = "标签-详情")
    @GetMapping("/{id}")
    public ResponseData detail(@PathVariable Long id){
        return ResponseData.success(tagService.detail(id));
    }

    @ApiOperation(value = "标签-列表", notes = "标签-列表")
    @GetMapping("/list")
    public ResponseData list(final TagRequest tagRequest){
        return ResponseData.success(tagService.list(tagRequest));
    }

    @ApiOperation(value = "标签-删除", notes = "标签-删除")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id){
        tagService.delete(id);
        return ResponseData.success();
    }

}
