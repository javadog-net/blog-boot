package net.javadog.blog.dto.response;

import lombok.Data;
import net.javadog.blog.common.response.BaseResponse;

import java.io.Serializable;

/**
 * 标签 response
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/

@Data
public class TagResponse extends BaseResponse implements Serializable {

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 状态（字典），1：正常；0：停用；-1：删除
     */
    private Integer status;

}