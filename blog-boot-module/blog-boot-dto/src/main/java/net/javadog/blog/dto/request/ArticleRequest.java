package net.javadog.blog.dto.request;

import lombok.Data;
import net.javadog.blog.common.request.BaseRequest;

import java.io.Serializable;

/**
 * 标签request
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/

@Data
public class ArticleRequest extends BaseRequest implements Serializable {

    /**
     * 分类id
     */
    private String catId;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 原始内容(markdown)
     */
    private String originContent;

    /**
     * 转化后内容(html)
     */
    private String convertContent;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 文章状态，1：已发布；0：草稿箱；2：已删除
     */
    private Integer articleStatus;

    /**
     * 标签Id
     */
    transient String tagId;

}