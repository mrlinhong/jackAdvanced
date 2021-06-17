package com.jack.jackAdvanced.filesystem.minio;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio客户端配置
 */
@Slf4j
@Configuration
public class MinioConfig {

    @Autowired
    private MinIOData minioData;

    /**
     * 初始化minio客户端,不用每次都初始化
     */
    @Bean
    public MinioClient minioClient() {
        try {
            MinioClient minioClient = new MinioClient(minioData.getUrl(), minioData.getAccessKey(), minioData.getSecretKey());
            this.checkBucketName(minioClient);
            return minioClient;
        }
        catch (final Exception e) {
            log.error("初始化minio出现异常:{}", e.fillInStackTrace());
            return null;
        }
    }

    public void checkBucketName(MinioClient minioClient) {
        String bucketName = minioData.getBucketName();
        try {
            boolean isExist = minioClient.bucketExists(minioData.getBucketName());
            if (isExist) {
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
