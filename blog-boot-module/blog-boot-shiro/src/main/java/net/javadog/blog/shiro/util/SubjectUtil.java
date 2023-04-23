package net.javadog.blog.shiro.util;

import lombok.extern.slf4j.Slf4j;
import net.javadog.blog.common.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

/**
 * 获取Subject工具类
 *
 * @author: hdx
 * @Date: 2022-06-13 17:48
 * @version: 1.0
 **/
@Slf4j
public class SubjectUtil {

    /**
     * 获取用户信息
     */
    public static Long getUserId() {
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();

        } catch (UnavailableSecurityManagerException e) {
            log.info("身份鉴权异常,{}", e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
        return (Long) subject.getPrincipal();
    }
}
