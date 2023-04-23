package net.javadog.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.blog.dto.request.TagRequest;
import net.javadog.blog.dto.response.TagResponse;
import net.javadog.blog.entity.Tag;

import java.util.List;

/**
 * 标签接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface TagService extends IService<Tag> {

    /**
     * 标签新增
     *
     * @param tagRequest
     * @return TagResponse
     */
    TagResponse save(TagRequest tagRequest);

    /**
     * 标签详情
     *
     * @param id
     * @return TagResponse
     */
    TagResponse detail(Long id);

    /**
     * 标签列表
     *
     * @param tagRequest
     * @return List<TagResponse>
     */
    List<TagResponse> list(TagRequest tagRequest);

    /**
     * 标签删除
     *
     * @param id
     * @return void
     */
    void delete(Long id);
}
