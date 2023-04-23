package net.javadog.blog.dto.request;

import lombok.Data;
import net.javadog.blog.common.request.BaseRequest;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 分类request
 *
 * @author: hdx
 * @Date: 2023-04-18 09:59
 * @version: 1.0
 **/

@Data
public class CategoryRequest extends BaseRequest implements Serializable {

    /**
     * 分类名称
     */
    @NotBlank(message = "请输入分类名称")
    private String catName;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（字典），1：正常；0：停用；-1：删除
     */
    private Integer status;


}