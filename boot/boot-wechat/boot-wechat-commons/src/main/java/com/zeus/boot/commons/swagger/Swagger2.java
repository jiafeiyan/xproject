package com.zeus.boot.commons.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zeus.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建api
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("红军蓝彩项目————接口测试平台")
                //创建人
                .contact(new Contact("Jaffe Yan", "http://localhost:8080", ""))
                //版本号
                .version("1.0")
                //描述
                .description("Restful API 接口统一测试平台")
                .build();
    }
}
