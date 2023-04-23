package net.javadog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.blog.entity.ArticleTag;
import net.javadog.blog.mapper.ArticleTagMapper;
import net.javadog.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public void deleteBatch(Long articleId) {
        // 首先覆盖原先分配过套餐的用户
        LambdaQueryWrapper<ArticleTag> removeWrapper = new LambdaQueryWrapper<>();
        removeWrapper
                .eq(ArticleTag::getArticleId, articleId);
        this.remove(removeWrapper);
    }

}




