package com.jack.jackAdvanced.filesystem.minio;

import lombok.Data;

/**
 * 文件上传返回结果
 */
@Data
public class MinioUploadDto {

    private String url;
    private String name;
}
