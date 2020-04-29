package com.ai.zhome.pms.content.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * wangzh 2020-4-29
 */
@Configuration
//扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MyBatisDataSourceConfig.MASTER_PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
@MapperScan(basePackages = MyBatisDataSourceConfig.SLAVE_PACKAGE, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class MyBatisDataSourceConfig {

    //数据库一扫描的mapper目录
    static final String MASTER_PACKAGE = "com.ai.zhome.pms.common.mapper";

    //数据库二扫描的mapper目录
    static final String SLAVE_PACKAGE = "com.ai.zhome.pms.common.slaveMapper";

    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary //默认使用该数据源
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

}
