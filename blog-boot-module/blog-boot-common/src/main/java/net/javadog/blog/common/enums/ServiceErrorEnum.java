package net.javadog.blog.common.enums;

import lombok.Getter;

/**
 * @Description: 业务枚举类
 * @Author: hdx
 * @Date: 2022/1/30 9:12
 * @Version: 1.0
 */
@Getter
public enum ServiceErrorEnum implements AbstractBaseExceptionEnum {

    // 业务错误
    USERNAME_EXIST_ERROR(1001, "此账号已存在!"),
    USERNAME_OR_PASSWORD_ERROR(1002, "账号或密码不正确!"),
    ORIGINAL_PASSWORD_ERROR(1003, "原密码不正确!"),

    // 文件错误
    FILE_IS_NULL(3001, "文件不能为空!"),
    FILE_MAX_POST_SIZE(3002, "文件大小超出最大限制!"),
    FILE_UPLOAD_ERROR(3003, "文件上传失败!"),
    ;

    /**
     * 错误码
     */
    private final Integer resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ServiceErrorEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
