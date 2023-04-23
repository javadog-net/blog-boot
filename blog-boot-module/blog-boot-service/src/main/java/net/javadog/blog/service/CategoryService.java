package net.javadog.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.blog.dto.request.CategoryRequest;
import net.javadog.blog.dto.request.TagRequest;
import net.javadog.blog.dto.response.CategoryResponse;
import net.javadog.blog.dto.response.TagResponse;
import net.javadog.blog.entity.Category;

import java.util.List;


/**
 * 分类接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface CategoryService extends IService<Category> {

    /**
     * 分类新增
     *
     * @param categoryRequest
     * @return CategoryResponse
     */
    CategoryResponse save(CategoryRequest categoryRequest);

    /**
     * 分类分页
     *
     * @param categoryRequest
     * @param current
     * @param size
     * @return CategoryResponse
     */
    IPage<CategoryResponse> page(CategoryRequest categoryRequest, Integer current, Integer  size);

    /**
     * 分类列表
     *
     * @param request
     * @return List<CategoryResponse>
     */
    List<CategoryResponse> list(CategoryRequest request);

    /**
     * 分类删除
     *
     * @param id
     * @return void
     */
    void delete(Long id);


}
