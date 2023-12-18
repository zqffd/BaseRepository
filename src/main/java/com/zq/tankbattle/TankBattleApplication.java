package com.zq.tankbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 作者:ZQ
 * 时间:2023/12/13 14:02
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@PropertySource(value = "classpath:application-dev.properties",encoding = "UTF-8")
public class TankBattleApplication {
    public static void main(String[] args){
        SpringApplication.run(TankBattleApplication.class,args);
    }
}
