package com.springbootdemo.generatordemo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Hello world!
 *
 */
public class GeneratorDemoApplication
{
    private static final String projectRootDir = System.getProperty("user.dir");
    private static final String coreRootDir = projectRootDir + "/generator-demo";
    public static void main(String[] args){
        System.out.println(coreRootDir);
        FastAutoGenerator
                .create("jdbc:mysql://localhost:3306/rbac_security_demo?useUnicode=true&characterEncoding=utf-8",
                        "root",
                        "chen8888")
                .globalConfig(builder -> {
                    builder
                            .author("chen") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(coreRootDir+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder-> {
                    builder
                            .parent("com.springbootdemo.rbacsecuritydemo")
                            .entity("po")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .other("utils");
                })
                .templateConfig(builder-> {
                    builder
                            .entity( "/templates/entity.java")
                            .service( "/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java");
                })
                .strategyConfig(builder -> {
                    builder
                            // 设置需要生成的表名
                            .addInclude("ums_permission","ums_role","ums_role_permission","ums_user","ums_user_role")
                            // 设置过滤表前缀
//                        .addTablePrefix("sys_", "c_")
                            .entityBuilder()
                            .enableLombok()
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .controllerBuilder()
                            .enableRestStyle()
                            .serviceBuilder().formatServiceFileName("%sService")
                            .entityBuilder().formatFileName("%sPo");
                })

                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
