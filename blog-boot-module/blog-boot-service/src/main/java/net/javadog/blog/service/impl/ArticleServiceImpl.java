package net.javadog.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.blog.common.enums.ArticleStatusEnum;
import net.javadog.blog.common.enums.CommonStatusEnum;
import net.javadog.blog.dto.request.ArticleRequest;
import net.javadog.blog.dto.request.CategoryRequest;
import net.javadog.blog.dto.response.ArticleResponse;
import net.javadog.blog.dto.response.CategoryResponse;
import net.javadog.blog.entity.Article;
import net.javadog.blog.entity.ArticleTag;
import net.javadog.blog.entity.Category;
import net.javadog.blog.mapper.ArticleMapper;
import net.javadog.blog.service.ArticleService;
import net.javadog.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleTagService articleTagService;

    @Override
    public ArticleResponse save(ArticleRequest request) {
        Article article = new Article();
        BeanUtil.copyProperties(request, article);
        article.setArticleStatus(request.getArticleStatus());
        this.saveOrUpdate(article);
        // 文章标签，先删后插
        articleTagService.deleteBatch(article.getId());
        // 插入标签
        String tagIds = request.getTagId();
        List<String> tagList = Arrays.asList(tagIds.split(","));
        List<ArticleTag> articleTags= new ArrayList<>();
        tagList.forEach(item->{
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(Long.valueOf(item));
            articleTags.add(articleTag);
        });
        articleTagService.saveBatch(articleTags);
        ArticleResponse response = new ArticleResponse();
        BeanUtil.copyProperties(article, response);
        return response;
    }

    @Override
    public ArticleResponse detail(Long id) {
        Article article = this.getById(id);
        // 更新访问量
        article.setView(article.getView()+1);
        this.updateById(article);
        ArticleResponse response = new ArticleResponse();
        BeanUtil.copyProperties(article, response);
        LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
        articleTagWrapper.eq(ArticleTag::getArticleId, article.getId());
        List<ArticleTag> list = articleTagService.list(articleTagWrapper);
        List<Long> tagIds = list.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        List<String> tagIdsStr = tagIds.stream().map(x -> x + "").collect(Collectors.toList());
        response.setTagIds(tagIdsStr);
        return response;
    }

    @Override
    public IPage<ArticleResponse> page(ArticleRequest request, Integer current, Integer size) {
        IPage<Article> page = new Page<>(current, size);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ObjectUtil.isNotNull(request.getArticleStatus()), Article::getArticleStatus, request.getArticleStatus())
                .like(StrUtil.isNotBlank(request.getTitle()), Article::getTitle, request.getTitle())
                .orderByDesc(Article::getUpdateTime);
        IPage<Article> ques = this.page(page, queryWrapper);

        IPage<ArticleResponse> convert = ques.convert(article ->{
            LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
            articleTagWrapper.eq(ArticleTag::getArticleId, article.getId());
            List<ArticleTag> list = articleTagService.list(articleTagWrapper);
            List<Long> tagIds = list.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
            article.setTagIds(tagIds);
            return BeanUtil.copyProperties(article, ArticleResponse.class);
        });
        return convert;
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Article::getArticleStatus, ArticleStatusEnum.DELETED.getCode()).eq(Article::getId, id);
        this.update(updateWrapper);
    }
}




