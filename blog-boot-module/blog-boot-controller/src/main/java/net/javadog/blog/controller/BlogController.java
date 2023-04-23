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
@RequestMapping("/blog/article")
public class BlogController {

    @Resource
    private ArticleService articleService;

    @ApiOperation(value = "文章-分页列表", notes = "文章-分页列表")
    @GetMapping
    public ResponseData page(final ArticleRequest request,Integer current, Integer size){
        request.setArticleStatus(1);
        return ResponseData.success(articleService.page(request, current, size));
    }

}
