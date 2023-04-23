package net.javadog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.blog.common.exception.ServiceException;
import net.javadog.blog.common.response.ResponseData;
import net.javadog.blog.dto.response.FileResponse;
import net.javadog.blog.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 文件上传
 *
 * @author: hdx
 * @Date: 2022-12-07 15:48
 * @version: 1.0
 **/
@RestController
@Api(tags = "上传控制器")
public class UploadController {

    @Resource
    private FileService fileService;


    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/upload")
    public ResponseData upload(MultipartFile file) {
        FileResponse fileResponse;
        try {
            fileResponse = fileService.upload(file);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
        return ResponseData.success(fileResponse, "上传成功");
    }
}
