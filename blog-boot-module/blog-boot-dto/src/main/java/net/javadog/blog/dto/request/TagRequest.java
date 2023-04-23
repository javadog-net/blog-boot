package net.javadog.blog.dto.request;

import lombok.Data;
import net.javadog.blog.common.request.BaseRequest;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 标签request
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/

@Data
public class TagRequest extends BaseRequest implements Serializable {

    /**
     * 标签名
     */
    @NotBlank(message = "请输入标签名")
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