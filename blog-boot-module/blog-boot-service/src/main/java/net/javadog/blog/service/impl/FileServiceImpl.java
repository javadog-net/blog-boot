package net.javadog.blog.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ObjectUtil;
import net.javadog.blog.common.enums.ServiceErrorEnum;
import net.javadog.blog.common.exception.ServiceException;
import net.javadog.blog.dto.response.FileResponse;
import net.javadog.blog.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author: hdx
 * @Date: 2023-01-13 13:37
 * @version: 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    /**
     * 文件上传最大限制
     */
    private static int MAX_POST_SIZE;

    @Value("${file.max-post-size}")
    public void setMaxPostSize(int maxPostSize) {
        MAX_POST_SIZE = maxPostSize;
    }

    /**
     * 静态附件前缀
     */
    private static String STATIC_PREFIX;

    @Value("${file.static-prefix}")
    public void setStaticPrefix(String staticPrefix) {
        STATIC_PREFIX = staticPrefix;
    }

    /**
     * 文件上传目录
     */
    private static String UPLOAD_FOLDER;

    @Value("${file.upload-folder}")
    public void setUploadFolder(String uploadFolder) {
        UPLOAD_FOLDER = uploadFolder;
    }

    /**
     * 固定数据大小
     */
    static int FIXED_NUMBER = 1024;

    /**
     * 固定数据大小
     */
    static String FIXED_SEPARATOR = "\\";

    @Override
    public FileResponse upload(MultipartFile multipartFile) throws IOException {
        // 参数检验
        if (ObjectUtil.isEmpty(multipartFile) || multipartFile.getSize() <= 0) {
            throw new ServiceException(ServiceErrorEnum.FILE_IS_NULL);
        }
        long size = multipartFile.getSize();
        // 文件限制10M
        if (size > MAX_POST_SIZE * FIXED_NUMBER * FIXED_NUMBER) {
            throw new ServiceException(ServiceErrorEnum.FILE_MAX_POST_SIZE);
        }
        // 设置时间戳文件包路径
        final String TIME_FOLDER = File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")) + File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")) + File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd")) + File.separator;
        // 文件包全路径
        final String fileFolder = UPLOAD_FOLDER + TIME_FOLDER;
        // 文件包是否存在
        if (!FileUtil.exist(fileFolder)) {
            FileUtil.mkdir(fileFolder);
        }
        // 获取UUID文件名
        String fileName = getPathName(multipartFile);
        // 文件路径
        String path = fileFolder + fileName;
        File file = new File(path);
        // 判断文件是否存在
        if (FileUtil.exist(file)) {
            return getFileVo(multipartFile, TIME_FOLDER + fileName, file);
        }
        // 写入
        File file1 = FileUtil.writeBytes(multipartFile.getBytes(), path);
        if (file1.length() < 0) {
            throw new ServiceException(ServiceErrorEnum.FILE_UPLOAD_ERROR);
        }
        // 获取返回路径
        String resultUrl = File.separator + STATIC_PREFIX + TIME_FOLDER + fileName;
        if (resultUrl.contains(FIXED_SEPARATOR)) {
            resultUrl = resultUrl.replaceAll("\\\\", "/");
        }
        return getFileVo(multipartFile, resultUrl, file);
    }


    /**
     * 此处自定义文件名,uuid + extension
     *
     * @param file
     * @return java.lang.String
     */
    private static String getPathName(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    /**
     * 获取扩展名
     *
     * @param file
     * @return java.lang.String
     */
    private static String getExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        return originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
    }

    /**
     * 整合FileVo
     *
     * @param originalFile
     * @param resUrl
     * @param nowFile
     * @return net.javadog.chat.common.vo.FileVo
     */
    private static FileResponse getFileVo(MultipartFile originalFile, String resUrl, File nowFile) {
        FileResponse fileVo = FileResponse.builder().nowFilename(FileNameUtil.getName(nowFile))
                .originalFilename(originalFile.getOriginalFilename())
                .extName(FileNameUtil.extName(nowFile))
                .fileType(FileTypeUtil.getType(nowFile))
                .fileSize(originalFile.getSize())
                .url(resUrl).build();
        return fileVo;
    }
}
