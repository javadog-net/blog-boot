package net.javadog.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签实体
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/
@TableName(value ="article_tag")
@Data
public class ArticleTag implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "articleId")
    private Long articleId;

    /**
     * 标签id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "tagId")
    private Long tagId;




}