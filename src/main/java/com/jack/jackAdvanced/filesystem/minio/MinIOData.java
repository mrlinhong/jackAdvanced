package com.jack.jackAdvanced.filesystem.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * minio属性文件
 * @author：林洪
 * @date：2021年6月17日
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIOData {

    /**
     * minio地址+端口号
     */
    private String url;

    /**
     * minio用户名
     */
    private String accessKey;

    /**
     * minio密码
     */
    private String secretKey;

    /**
     * 文件桶的名称
     */
    private String bucketName;
}
