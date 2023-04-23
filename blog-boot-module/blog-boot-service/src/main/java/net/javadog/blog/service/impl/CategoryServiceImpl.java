package net.javadog.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.blog.common.enums.CommonStatusEnum;
import net.javadog.blog.dto.request.CategoryRequest;
import net.javadog.blog.dto.response.CategoryResponse;
import net.javadog.blog.dto.response.TagResponse;
import net.javadog.blog.entity.Category;
import net.javadog.blog.entity.Tag;
import net.javadog.blog.mapper.CategoryMapper;
import net.javadog.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 分类接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryRequest, category);
        category.setStatus(CommonStatusEnum.YES.getCode());
        this.saveOrUpdate(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtil.copyProperties(category, categoryResponse);
        return categoryResponse;
    }

    @Override
    public IPage<CategoryResponse> page(CategoryRequest categoryRequest, Integer current, Integer size) {
        IPage<Category> page = new Page<>(current, size);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, CommonStatusEnum.YES.getCode())
                .like(StrUtil.isNotBlank(categoryRequest.getCatName()), Category::getCatName, categoryRequest.getCatName())
                .orderByDesc(Category::getUpdateTime);
        IPage<Category> ques = this.page(page, queryWrapper);
        IPage<CategoryResponse> convert = ques.convert(Category -> BeanUtil.copyProperties(Category, CategoryResponse.class));
        return convert;
    }

    @Override
    public List<CategoryResponse> list(CategoryRequest request) {
        List<CategoryResponse> resultList = CollectionUtil.newArrayList();
        LambdaQueryWrapper<Category> query = new LambdaQueryWrapper<>();
        query.like(StrUtil.isNotBlank(request.getCatName()), Category::getCatName, request.getCatName())
                .eq(Category::getStatus, CommonStatusEnum.YES.getCode());
        List<Category> categoryList = this.list(query);
        categoryList.forEach(item->{
            CategoryResponse response = new CategoryResponse();
            BeanUtil.copyProperties(item, response);
            resultList.add(response);
        });
        return resultList;
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Category::getStatus, CommonStatusEnum.NO.getCode()).eq(Category::getId, id);
        this.update(updateWrapper);
    }
}




