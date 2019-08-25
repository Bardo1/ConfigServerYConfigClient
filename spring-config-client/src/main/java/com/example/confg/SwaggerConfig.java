package com.example.confg;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.SpringConfigClientApplication;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* SwaggerConfig. 
* Clase de configuracíon de la interfaz de test de controllador con swagger
* url de despliegue: http://localhost:8080/swagger-ui.html
* @author Walter Muñoz
* 
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                  
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
  	      .apis(RequestHandlerSelectors.basePackage(SpringConfigClientApplication.class.getPackage().getName()))
          .paths(PathSelectors.any())                          
          .build()
      	  .apiInfo(apiInfo());               

    }
    
    
    private ApiInfo apiInfo() {
		return new ApiInfo(
				"Test de microservicio",
				"Componente para el manejo de razas de perros",
				"0.0.1",
				null,
				new Contact("Walter Muñoz", "https://www.linkedin.com/in/walterrmz/", "walterrmz1@gmail.com"),
				null, 
				null,
				Collections.emptyList());
	}
        

}


