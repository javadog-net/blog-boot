package net.javadog.blog.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 文件组装返回参数
 * @Author: hdx
 * @Date: 2022/2/9 15:53
 * @Version: 1.0
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel("文件组装返回参数")
@Builder
public class FileResponse implements Serializable {

    private static final long serialVersionUID = -6805582643001421717L;

    /**
     * 原文件名
     */
    @ApiModelProperty("原文件名")
    private String originalFilename;

    /**
     * 现文件名
     */
    @ApiModelProperty("现文件名")
    private String nowFilename;

    /**
     * 扩展名
     */
    @ApiModelProperty("现文件名")
    private String extName;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;

    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Long fileSize;

    /**
     * 资源路径
     */
    @ApiModelProperty("资源路径")
    private String url;

}
