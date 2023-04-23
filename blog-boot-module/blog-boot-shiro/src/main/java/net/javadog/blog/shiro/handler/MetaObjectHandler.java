package net.javadog.blog.shiro.handler;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import net.javadog.blog.shiro.util.SubjectUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Description: MybatisPlus属性自动填充配置
 * @author: hdx
 * @Date: 2022-06-16 16:39
 * @version: 1.0
 **/
@Slf4j
@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = SubjectUtil.getUserId();
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
        this.setFieldValByName("createTime", DateUtil.date(), metaObject);
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = SubjectUtil.getUserId();
        this.setFieldValByName("updateBy", userId, metaObject);
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }
}
