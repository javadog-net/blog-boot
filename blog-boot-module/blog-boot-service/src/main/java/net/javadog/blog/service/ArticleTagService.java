package net.javadog.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.blog.entity.ArticleTag;

import java.util.List;

/**
 * 文章标签接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 文章标签删除
     *
     * @param articleId
     */
    void deleteBatch(Long articleId);
}
