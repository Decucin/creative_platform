package com.power.platform.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                //.enable(false) 是否启用swagger, 可以在yml文件里面配置,实现控制多环境是否启用swagger
                //.groupName("aa") 设置该Docket实例的名字, 可以实现多个Docket ,实现分组
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors.basePackage 配置要扫描接口的方式,
                // basePackage 指定要扫描的包, .any() 扫描全部 .none()都不扫描
                //withMethodAnnotation 扫描所有有注解(@ApiOperation)的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智能创作平台")
                .description("智能创作平台接口信息")
                .contact(new Contact("power","https://www.github.com/decucin","1690406856@qq.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "user-token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}


