package com.jack.jackAdvanced.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Base64Utils {

    /**
     *  文件转base64
     * @param path
     * @return
     */
    public static String ImgToBase64(String path) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(new File(path));
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回Base64编码过的字节数组字符串
        return Base64.encodeBase64String(data);
    }
}
