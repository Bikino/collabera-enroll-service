package com.collabera.interview.enroll.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Enroll microservice Documentation ",
            "API Documentations for Enroll microservice",
            "1.0", " ",
            "Ildephonse BIKINO",
            "Apache 2.0", "http://www.apache.org/licemses/LICENSE-2.0");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.collabera.interview")).build();
    }
}
