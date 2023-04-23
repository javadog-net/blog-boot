package net.javadog.blog.service;

import net.javadog.blog.dto.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface FileService {
    FileResponse upload(MultipartFile file) throws IOException;
}
