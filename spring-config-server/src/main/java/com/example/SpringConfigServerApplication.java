package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
* SpringConfigServerApplication.
* Clase donde arranca nuestro servidor de configuración, a través
* de la anotación EnableConfigServer.
* 
* @author Walter Muñoz
* 
*/
@EnableConfigServer
@SpringBootApplication
public class SpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerApplication.class, args);
	}
}
