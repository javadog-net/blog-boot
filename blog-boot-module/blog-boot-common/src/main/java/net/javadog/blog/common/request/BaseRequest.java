package net.javadog.blog.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Request
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/

@Data
public class BaseRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}