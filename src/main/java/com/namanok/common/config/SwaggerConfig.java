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
 * swagger ����
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		//Swagger���� �����ϴ� �����ڵ忡 ���� �⺻ �޽��� ��� ����
                .useDefaultResponseMessages(false)
                
                //ApiSelectorBuilder ����
                .select()
                
                //Swagger API �������� BasePackage
                .apis(RequestHandlerSelectors.basePackage("com.namanok.controller"))
                
                //�ش� URL ��û�� Swagger API ������ ����
                .paths(PathSelectors.ant("/**"))
                .build()
                
                //wagger API ��������
                .apiInfo(apiInfoData())
                
                //��û�� ���ԵǾ�� �� Authorization �����, ���������� �����Ͽ� ����ϵ��� ����
                .securityContexts(Arrays.asList(securityContext()))
                
                //Authorize ��ư ����
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /**
     * wagger API ��������
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