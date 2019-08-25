package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
* SpringConfigClientApplication. 
* Clase de donde arranca nuestro cliente del config server
* 
* @author Walter Muñoz
* 
*/
@EnableConfigurationProperties
@SpringBootApplication
public class SpringConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigClientApplication.class, args);
	}
}


