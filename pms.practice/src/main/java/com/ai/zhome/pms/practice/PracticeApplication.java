package com.ai.zhome.pms.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication {
    public static void main(String[] args){
        SpringApplication.run(PracticeApplication.class, args);
    }
}

/*
 * 1. SpringBoot会自动加载数据源,如在配置文件未配置则报异常。取消启动时自动加载数据源，在application类配置
 *    @SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
 *    该配置会忽略数据源配置(没有配置，或者配置数据源错误都忽略)
 * 2. 按ctrl+F9,重新自动构建
 */
