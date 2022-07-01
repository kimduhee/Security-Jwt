package com.namanok.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * swagger 설정
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		//Swagger에서 제공하는 응답코드에 대한 기본 메시지 사용 여부
                .useDefaultResponseMessages(false)
                
                //ApiSelectorBuilder 생성
                .select()
                
                //Swagger API 문서생성 BasePackage
                .apis(RequestHandlerSelectors.basePackage("com.namanok.controller"))
                
                //해당 URL 요청만 Swagger API 문서로 생성
                .paths(PathSelectors.ant("/**"))
                .build()
                
                //wagger API 문서설명
                .apiInfo(apiInfoData())
                
                //요청에 포함되어야 할 Authorization 헤더를, 전역적으로 지정하여 사용하도록 설정
                .securityContexts(Arrays.asList(securityContext()))
                
                //Authorize 버튼 생성
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /**
     * wagger API 문서설명
     * 
     * @return
     */
    private ApiInfo apiInfoData() {
        return new ApiInfoBuilder()
        		.title("My rest API")
                .description("My rest API")
                .version("0.0.1")
//                .license("License")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
    	return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}