//package com.ws.ldy;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//
///**
// * 启动类 (修改架构可直接使用该模块)
// * - exclude = DruidDataSourceAutoConfigure.class 为排除默认数据源
// * - @MapperScan 为mapper 扫包路径
// * - @EnableAdminServer 为监控Admin中心,访问地址为项目路径+ /bootAdmin, yml中配置
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2019/10/31 20:45
// * @version 1.0.0
// */
//@SpringBootApplication(scanBasePackages = { "com.ws.ldy"}, exclude = DruidDataSourceAutoConfigure.class)
//@MapperScan({"com.ws.ldy.*.*.mapper"})
////@EnableAdminServer     // springbootAdmin
////@ServletComponentScan
//public class XjBaseAdminServer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(XjBaseAdminServer.class, args);
//    }
//}
//
