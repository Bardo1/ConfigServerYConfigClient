package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
* SpringConfigClientApplicationTests. 
* Clase donde realizamos algunas pruebas de test unitarios con Junit
* 
* @author Walter Muñoz
* 
*/
@RefreshScope
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConfigClientApplicationTests {
    
	//Con esto asignamos el valor del conf server a la variable y además asignamos un valor sea correcta o no.
	@Value("${url1: No funciona de forma correcta conf server}")
	private String url1;
	
	@Value("${url2: No funciona de forma correcta conf server}")
	private String url2;
	 
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testValidaUrlSubRazas() {
		Assert.assertEquals("https://dog.ceo/api/breed/{breed name}/list", this.url1);
	}
	
	
	@Test
	public void testValidaUrlImagenes() {
		Assert.assertEquals("https://dog.ceo/api/breed/{breed name}/images", this.url2);
	}
	
	@Test
	public void testGetImagesListSuccess() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String raza="bulldog";
	    final String baseUrl = this.url1.replace("{breed name}",raza); 
	    URI uri = new URI(baseUrl);
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("message"));
	}
	
	@Test
	public void testGetBreedsListSuccess() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String raza="bulldog";
	    final String baseUrl = this.url2.replace("{breed name}",raza); 
	    URI uri = new URI(baseUrl);
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("message"));
	}

}
