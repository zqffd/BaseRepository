package com.zq.tankbattle.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 作者:ZQ
 * 时间:2023/12/15 16:03
 */
@Component
public class TankCommon {


    private static File file;

    //坦克数量
    public static Integer badTankNum;


    @Value("${badTankNum}")
    public void setBadTankNum(Integer badTankNum) {
        TankCommon.badTankNum = badTankNum;
    }


    @Bean
    public static Integer getBadTankNum() {
        return badTankNum;
    }

    //私有化，不让其它类new这个对象，
    private TankCommon() {}




    /**
      Author: ZQ
      Date: 2023/12/18
      Decription: 返回int类型的值
    */
    public static Integer getIntValue(String key){
      return Integer.parseInt((String) mainConfig(key));
    }


    /**
      Author: ZQ
      Date: 2023/12/18
      Decription: 返回字符串类型的值
    */
    public static String getStringValue(String key){
        return  (String)mainConfig(key);
    }





    /**
      Author: ZQ
      Date: 2023/12/15
      Decription: 读取properties文件中的配置，
    */
    public static Object mainConfig(String key) {
        try {
            if (null == file || !file.exists()){
                 file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "application-dev.properties");
            }

            FileInputStream in = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(in);
            return properties.get(key).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






}
