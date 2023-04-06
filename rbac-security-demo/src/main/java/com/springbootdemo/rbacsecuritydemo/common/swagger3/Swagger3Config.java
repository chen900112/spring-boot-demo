package com.springbootdemo.rbacsecuritydemo.common.swagger3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi // 启用 Swagger3 功能注解
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springbootdemo.rbacsecuritydemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        // 设置 API 文档信息
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger3 构建 API 文档")
                .description("更多示例请关注：https://github.com/hfeng1016/spring-boot-demo")
                .version("1.0")
                .build();
    }
}
