package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.CategoryRequest;
import net.javadog.blog.dto.request.TagRequest;
import net.javadog.blog.service.CategoryService;
import net.javadog.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分类控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "分类控制器")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "分类-新增", notes = "分类-新增")
    @PostMapping
    public ResponseData save(@RequestBody CategoryRequest request){
        categoryService.save(request);
        return ResponseData.success();
    }

    @ApiOperation(value = "分类-分页列表", notes = "分类-分页列表")
    @GetMapping
    public ResponseData page(final CategoryRequest request,Integer current, Integer size){
        return ResponseData.success(categoryService.page(request, current, size));
    }

    @ApiOperation(value = "分类-列表", notes = "分类-列表")
    @GetMapping("/list")
    public ResponseData list(final CategoryRequest request){
        return ResponseData.success(categoryService.list(request));
    }

    @ApiOperation(value = "分类-删除", notes = "分类-删除")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseData.success();
    }

}
