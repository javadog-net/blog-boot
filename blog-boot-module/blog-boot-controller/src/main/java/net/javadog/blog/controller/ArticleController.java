package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.request.ArticleRequest;
import net.javadog.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章控制器
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@Api(tags = "文章控制器")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation(value = "文章-新增", notes = "文章-新增")
    @PostMapping
    public ResponseData save(@RequestBody ArticleRequest request){
        articleService.save(request);
        return ResponseData.success();
    }

    @ApiOperation(value = "文章-分页列表", notes = "文章-分页列表")
    @GetMapping
    public ResponseData page(final ArticleRequest request,Integer current, Integer size){
        return ResponseData.success(articleService.page(request, current, size));
    }

    @ApiOperation(value = "文章-删除", notes = "文章-删除")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id){
        articleService.delete(id);
        return ResponseData.success();
    }

    @ApiOperation(value = "文章-详情", notes = "文章-详情")
    @GetMapping("/{id}")
    public ResponseData detail(@PathVariable Long id){
        return ResponseData.success(articleService.detail(id));
    }

}
