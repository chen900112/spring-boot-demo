package com.springbootdemo.rbacsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Hello world!
 *
 */
@EnableOpenApi
@MapperScan("com.springbootdemo.rbacsecuritydemo.mapper")
@SpringBootApplication
public class RbacSecurityDemoApplication
{
    public static void main( String[] args )
    {
      SpringApplication.run(RbacSecurityDemoApplication.class, args);
    }
}
