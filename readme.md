## ZHome项目管理系统(Program Management System)PMS

* ZHome项目管理系统

---------------

## Requirements

* jdk 1.8+
* org.springframework.boot 2.1.8.RELEASE

## common子项目
* 主要用来与数据库的交互,进行mapper对象的编码
* 采用mybatis-spring-boot-starter

## content子项目
* 内容管理系统

## source子项目
* 主要用来项目学习，代码练习和测试

## 子项目创建流程
*具体参考本人博客,地址[ZHome](https://blog.csdn.net/Wang_ZhongH/article/details/103989663 "ZHome")

## 已完成的功能

* 内容管理
   * CP管理
   * SP管理
   * 图片管理
   * 目录管理
* 应用配置管理
   * 推荐位排期审核
   * 推荐位管理
* 系统管理
   * 账号管理
   * 日志审查
   * 标签管理

## 待完成功能

* 报表
     * 对用户行为进行统计分析，形成报表
     
## 程序结构

   ### 程序入口
      * com/ai/zhome/pms/source/Application.java，application.yml中的spring.profiles.active指定加载的配置文件后缀
      * 访问方式为http://ip:port/login
   ### 主要功能模块
      * 内容新增
      * 内容审核
      * 内容组合
      * 推荐位排期以及审核
   ### 目录结构（根目录com/ai/zhome/pms/source）：
      - Application --程序入口
      - conf --配置文件
      - ctrl --控制层	      
      - mapper --数据库操作dao
      - model --数据传输对象
      - param --枚举等参数类
      - po --持久化对象
      - service --服务 
      - util --工具类
	  - resourse --存放页面和js
   ### 内容新增/审核ProviderDataController，业务流程为：
      1. 程序启动，用户登陆管理平台
      2. 点击cp内容管理或sp应用管理
      3. 点击新增按钮或批量导入内容可新增内容
      4. 新增完的内容都是待审核的状态
      5. 点击审核内容可审核内容状态
   
