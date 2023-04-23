package net.javadog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.javadog.blog.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * 分类实体
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/
@TableName(value ="category")
@Data
public class Category extends BaseEntity implements Serializable {
    /**
     * 分类名称
     */
    @TableField(value = "cat_name")
    private String catName;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态（字典），1：正常；0：停用；-1：删除
     */
    @TableField(value = "status")
    private Integer status;

}