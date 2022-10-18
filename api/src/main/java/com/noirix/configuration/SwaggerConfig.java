package com.noirix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI().info(apiInfo());
//    }
//
//    private Info apiInfo() {
//        return new Info()
//                .title("API")
//                .description("API")
//                .version("2.0")
//                .license(apiLicence());
//    }
//
//    private License apiLicence() {
//        return new License()
//                .name("MIT Licence")
//                .url("opensource.org/licenses/mit-license.php");
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
