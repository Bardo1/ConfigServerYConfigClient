package com.example.modelo;

import java.util.ArrayList;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Clase que utilizaremos para el mapeo de la 
* respuesta de la api pública. 
* - Utilizamos la libreria lombok para getter y setters
* 
* @author Walter Muñoz
* 
*/
@ToString(of = { "status", "message", "code" })
@EqualsAndHashCode(of = { "status", "message", "code" })
public class RespApi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String status;
	
	@Getter
	@Setter
	private ArrayList<String> message;
	
	@Getter
	@Setter
    private String code;
    
}





