package com.zq.tankbattle.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;

/**
 * 作者:ZQ
 * 时间:2023/12/14 14:04
 * 针对图片加载
 */
public class Utils {
    /**
      Author: ZQ
      Date: 2023/12/14
      Decription: 获取静态文件，入参：相对路径
    */
    public File getStaticFile(String relativePath) {
        File file = null;
        try {
            String filePath = ResourceUtils.CLASSPATH_URL_PREFIX+ relativePath;
            //Linux服务器获取，没获取到按本地获取，this.getClass主要是为了知道需要使用什么类加载器去找到这个相对路径的文件
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(filePath);
            if (null == input) {
                file = ResourceUtils.getFile(filePath);

            } else {
                //该路径为服务器上的相对路径
                file=new File(filePath);
                //input文件流转换为file文件，需要导入commons包
                FileUtils.copyInputStreamToFile(input, file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

}
