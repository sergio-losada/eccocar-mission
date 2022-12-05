package com.eccocar.mission;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
  @Bean
  public Docket apiDocket() {
    // http://localhost:8080/swagger-ui.html
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.eccocar.mission.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo("Mission REST API", 
        "REST API for managing space missions", 
        "1.0", 
        "",
        new Contact("Sergio Losada", "https://github.com/sergio-losada/eccocar-mission", "sergio.losada@outlook.com"), 
        "LICENSE", 
        "https://drive.google.com/file/d/1_s8gJFiE_i9jby_hGbcbFaO2iupr_Jsd/view?usp=sharing", 
        Collections.emptyList());
  }
}
