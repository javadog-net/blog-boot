package net.javadog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.javadog.blog.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 文章实体
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/
@TableName(value ="article")
@Data
public class Article extends BaseEntity implements Serializable {
    /**
     * 分类id
     */
    @TableField(value = "cat_id")
    private String catId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 原始内容(markdown)
     */
    @TableField(value = "origin_content")
    private String originContent;

    /**
     * 转化后内容(html)
     */
    @TableField(value = "convert_content")
    private String convertContent;

    /**
     * 浏览量
     */
    @TableField(value = "view")
    private Integer view;

    /**
     * 文章状态，1：已发布；0：草稿箱；2：已删除
     */
    @TableField(value = "article_status")
    private Integer articleStatus;


    /**
     * 标签Id
     */
    transient private List<Long> tagIds;

}