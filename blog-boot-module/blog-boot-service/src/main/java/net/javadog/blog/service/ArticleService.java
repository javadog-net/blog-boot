package net.javadog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.blog.dto.request.ArticleRequest;
import net.javadog.blog.dto.request.CategoryRequest;
import net.javadog.blog.dto.response.ArticleResponse;
import net.javadog.blog.dto.response.CategoryResponse;
import net.javadog.blog.entity.Article;

import java.util.List;

/**
 * 文章接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface ArticleService extends IService<Article> {

    /**
     * 文章新增
     *
     * @param request
     * @return ArticleResponse
     */
    ArticleResponse save(ArticleRequest request);

    /**
     * 文章详情
     *
     * @param id
     * @return ArticleResponse
     */
    ArticleResponse detail(Long id);

    /**
     * 分类分页
     *
     * @param request
     * @param current
     * @param size
     * @return CategoryResponse
     */
    IPage<ArticleResponse> page(ArticleRequest request, Integer current, Integer  size);

    /**
     * 文章删除
     *
     * @param id
     * @return void
     */
    void delete(Long id);
}
