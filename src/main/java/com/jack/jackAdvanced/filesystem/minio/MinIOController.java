package com.jack.jackAdvanced.filesystem.minio;


import com.google.api.client.util.IOUtils;
import com.jack.jackAdvanced.result.Result;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jack.jackAdvanced.result.CodeMsg.BIND_ERROR;


/**
 * minio上传,下载,删除接口
 * @author：linhong
 * @date：2021年6月17日
 */

@RequestMapping("/minio")
@RestController
public class MinIOController {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinIOData minioData;

    /**
     * 下载文件
     */
    @GetMapping(value = "/download")
    @SneakyThrows(Exception.class)
    public void download(@RequestParam("objectName") String objectName, HttpServletResponse response) {
        InputStream in = null;
        final ObjectStat stat = minioClient.statObject(minioData.getBucketName(), objectName);
        response.setContentType(stat.contentType());
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName, "UTF-8"));
        in = minioClient.getObject(minioData.getBucketName(), objectName);
        IOUtils.copy(in, response.getOutputStream());
        in.close();
    }


    /**
     * 上传文件
     */
    @PostMapping(value = "/upload")
    @SneakyThrows(Exception.class)
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
           return Result.error(BIND_ERROR);
        } else {
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            minioClient.putObject(minioData.getBucketName(), objectName, file.getInputStream(), file.getContentType());
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(minioData.getUrl() + "/" + minioData.getBucketName() + "/" + objectName);
            return Result.success(minioUploadDto);
        }
    }

    /**
     * 删除文件
     */
    @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SneakyThrows(Exception.class)
    public Result<String> delete(@RequestParam("fileName") String objectName) {
        minioClient.removeObject(minioData.getBucketName(), objectName);
        return Result.success("删除成功");
    }

}
