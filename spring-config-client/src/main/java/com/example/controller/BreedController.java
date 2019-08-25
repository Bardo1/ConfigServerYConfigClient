package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.modelo.RespApi;
import com.example.modelo.RespServ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
* Controlador de razas. 
* Se encarga de hacer la lógica principal para el 
* retorno de datos del requerimiento 
* 
* @author Walter Muñoz
* 
*/
@RefreshScope
@RestController
@CrossOrigin
@Api(tags = "Controlador de Razas de perros")
@RequestMapping("/api/v1")
class BreedController {
	
	//Con esto asignamos el valor del conf server a la variable y además asignamos un valor sea correcta o no.
	@Value("${url1: No funciona de forma correcta conf server}")
	private String url1;
	
	@Value("${url2: No funciona de forma correcta conf server}")
	private String url2;
	
	@Value("${msg: info: Config Server no esta funcionando..Revisar}")
	private String msg;
	
	//Para capturar los errores de excepción.
	public final Logger logger = LoggerFactory.getLogger(BreedController.class);
	
	/**
    * Metodo que utiliza dos url de llamada dinamica para realiza dos llamadas a la api publica
    * y hace una unión de datos de respuesta, para contruir la respuesta final del servicio.
    *
    * @param raza. El nombre de la raza del animal
    * @return ResponseEntity<RespServ>
    */
	@ApiOperation(value = "Endpoint para traer los datos requeridos por una raza")
    @GetMapping(value="list/{raza}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RespServ> listaBreed(@ApiParam(value = "raza", required = true , defaultValue = "bulldog")@PathVariable("raza") String raza) throws JsonProcessingException{
	    
   		RestTemplate restTemplate = new RestTemplate();
		
   		boolean elStatus=true;
		
	    String newUrl1 = this.url1.replace("{breed name}",raza);	   
	    String newUrl2 = this.url2.replace("{breed name}",raza);	 
		
	    RespServ reServ= new RespServ();
		RespApi resIma= new RespApi();
		RespApi resBre= new RespApi();

		try {
			String jsonBre = restTemplate.getForObject(newUrl1, String.class);
			resBre = new ObjectMapper().readValue(jsonBre, RespApi.class);	
			reServ.setSubBreeds(resBre.getMessage());
		} catch (Exception e) {
			elStatus=false;
			logger.info("An exception occurred: {}!", e.getMessage());
		}
		
		
		try {
			String jsonIma = restTemplate.getForObject(newUrl2, String.class);
			resIma = new ObjectMapper().readValue(jsonIma, RespApi.class);
			reServ.setImages(resIma.getMessage());
		}catch (Exception e) {
			elStatus=false;
			logger.info("An exception occurred: {}!", e.getMessage());
        }
		
		reServ.setBreed(raza);
		if(elStatus) {
			reServ.setStatus("Consulta realizada con exito");
		}else {
    		// Cuando no encuentra ningún nombre
			reServ.setBreed(null);
			reServ.setStatus("Error");
		}

		return new ResponseEntity<RespServ>(reServ, HttpStatus.OK);
	}
    
    
	@ApiOperation(value = "Endpoint para traer una variable desde el config server")
    @GetMapping(value="msg", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> getMsg() throws JsonProcessingException{    	
		return new ResponseEntity<String>(this.msg, HttpStatus.OK);
    }
    
}
