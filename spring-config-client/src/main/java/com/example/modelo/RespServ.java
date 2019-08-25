package com.example.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Clase que utilizaremos para el mapeo de la 
* respuesta del servicio.
* - Utilizamos la libreria lombok para getter y setters
* 
* @author Walter Muñoz
* 
*/

@ToString(of = { "breed", "subBreeds", "images", "status"})
@EqualsAndHashCode(of = { "breed", "subBreeds", "images", "status"})
public class RespServ implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String breed;
	
	@Getter
	@Setter
	private ArrayList<String> subBreeds;
	
	@Getter
	@Setter
	private ArrayList<String> images;
	
	@Getter
	@Setter
	private String status;
	
}
