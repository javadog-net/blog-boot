package net.javadog.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.javadog.blog.common.enums.CommonStatusEnum;
import net.javadog.blog.dto.request.TagRequest;
import net.javadog.blog.dto.response.TagResponse;
import net.javadog.blog.entity.Tag;
import net.javadog.blog.mapper.TagMapper;
import net.javadog.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签接口实现类
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public TagResponse save(TagRequest tagRequest) {
        Tag tag = new Tag();
        BeanUtil.copyProperties(tagRequest, tag);
        tag.setStatus(CommonStatusEnum.YES.getCode());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getTagName, tag.getTagName()).eq(Tag::getStatus, CommonStatusEnum.YES.getCode());
        List<Tag> list = this.list(queryWrapper);
        if(CollectionUtil.isNotEmpty(list)){
            tag = list.get(0);
        }
        this.saveOrUpdate(tag);
        TagResponse tagResponse = new TagResponse();
        BeanUtil.copyProperties(tag, tagResponse);
        return tagResponse;
    }

    @Override
    public TagResponse detail(Long id) {
        Tag tag = this.getById(id);
        TagResponse tagResponse = new TagResponse();
        BeanUtil.copyProperties(tag, tagResponse);
        return tagResponse;
    }

    @Override
    public List<TagResponse> list(TagRequest tagRequest) {
        List<TagResponse> resultList = CollectionUtil.newArrayList();
        LambdaQueryWrapper<Tag> query = new LambdaQueryWrapper<>();
        query.like(StrUtil.isNotBlank(tagRequest.getTagName()), Tag::getTagName, tagRequest.getTagName())
                .eq(StrUtil.isNotBlank(tagRequest.getColor()), Tag::getColor, tagRequest.getTagName())
                .eq(Tag::getStatus, CommonStatusEnum.YES.getCode());
        List<Tag> tagList = this.list(query);
        tagList.forEach(item->{
            TagResponse tagResponse = new TagResponse();
            BeanUtil.copyProperties(item, tagResponse);
            resultList.add(tagResponse);
        });
        return resultList;
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Tag::getStatus, CommonStatusEnum.NO.getCode()).eq(Tag::getId, id);
        this.update(updateWrapper);
    }
}




