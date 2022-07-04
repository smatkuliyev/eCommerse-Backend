package com.edu.ecom.config;

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
public class SwaggerConfig {

    //http://localhost:8080/swagger-ui.html
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edu.ecom"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        Contact contact = new Contact("webecom", "http://webecom.com", "contact.webecom@gmail.com");
        return new ApiInfoBuilder()
                .title("Ecommerse API")
                .description("documentation Ecommerse api")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licences/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}
