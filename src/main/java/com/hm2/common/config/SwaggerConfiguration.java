package com.hm2.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${application.swagger.enable:true}")
    private boolean externallyConfiguredFlag;

    @Value("${application.swagger.token}")
    private String token;

    @Bean
    public Docket selectApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(externallyConfiguredFlag)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
//    @Bean
//    public Docket selectApiUms() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(externallyConfiguredFlag)
//                .groupName("Ums")
//                .select()
//                .paths(PathSelectors.ant("/api/ums/**"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//    @Bean
//    public Docket selectApiLookup() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(externallyConfiguredFlag)
//                .groupName("Lookup")
//                .select()
//                .paths(PathSelectors.ant("/api/lookup/**"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//    @Bean
//    public Docket selectApiLogin() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(externallyConfiguredFlag)
//                .groupName("Login")
//                .select()
//                .paths(PathSelectors.ant("/authenticate"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//    @Bean
//    public Docket selectApiSystem() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(externallyConfiguredFlag)
//                .groupName("System")
//                .select()
//                .paths(PathSelectors.ant("/system/**"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public SecurityConfiguration security() {
        String swaggerToken = token;
        return new SecurityConfiguration(
                null,
                null,
                null,
                null,
                "Bearer " + swaggerToken, ApiKeyVehicle.HEADER,
                "Authorization", ",");
    }
}
