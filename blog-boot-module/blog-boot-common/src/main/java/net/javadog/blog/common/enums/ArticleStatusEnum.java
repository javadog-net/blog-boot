package net.javadog.blog.common.enums;

import lombok.Getter;

/**
 * 文章状态枚举类
 *
 * @author: hdx
 * @Date: 2022-08-08 16:03
 * @version: 1.0
 **/
@Getter
public enum ArticleStatusEnum {
    // 草稿箱
    DRAFTS(0, "草稿箱"),
    // 已发布
    PUBLISHED(1, "已发布"),
    // 已删除
    DELETED(2, "已删除"),
    ;

    private final Integer code;
    private final String message;

    ArticleStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
