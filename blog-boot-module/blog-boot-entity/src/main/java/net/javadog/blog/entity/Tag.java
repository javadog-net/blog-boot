package net.javadog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.javadog.blog.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * 标签实体
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/
@TableName(value ="tag")
@Data
public class Tag extends BaseEntity implements Serializable {
    /**
     * 标签名
     */
    @TableField(value = "tag_name")
    private String tagName;

    /**
     * 颜色
     */
    @TableField(value = "color")
    private String color;

    /**
     * 状态（字典），1：正常；0：停用；-1：删除
     */
    @TableField(value = "status")
    private Integer status;

}