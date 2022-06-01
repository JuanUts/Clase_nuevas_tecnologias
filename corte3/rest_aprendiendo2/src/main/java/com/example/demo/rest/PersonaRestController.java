package com.example.demo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Persona;
import com.example.demo.services.IPersonaService;

@RestController
@RequestMapping("api")
public class PersonaRestController {

	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/personas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Persona> listadoDePersonas(){
		
		return this.personaService.findAll();
	}
	
	@GetMapping("/persona/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Persona> listarPersona(@PathVariable(name = "id", required = true)int id) {
		
		Persona persona = this.personaService.findById(id);
		
		if(persona == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		
		
		//return this.personaService.findById(id);
		
	}
	
	@PostMapping("/persona/guardar")
	public ResponseEntity<Map<String, Object>> guardar(@RequestBody Persona persona, BindingResult bindingResult) {
		
		Map<String, Object> respuesta = new HashMap<>();
		
		if(bindingResult.hasErrors()) {
			
			List<String> errores = bindingResult.getFieldErrors().stream().map(error -> "El campo " + error.getField() + " " + error.getDefaultMessage()).toList();
			
			respuesta.put("errores", errores);
			
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.BAD_REQUEST);
			
		}
		
		try {
			
			Persona personaSave = this.personaService.save(persona);
			
			respuesta.put("mensaje", "la persona se ha creado con exito");
			respuesta.put("persona", personaSave);
			
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.CREATED);
			
		} catch (DataAccessException e) {
			
			respuesta.put("mensaje", "Error al realizar el insert en la base de datos");
			respuesta.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/persona/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable(name = "id", required = true)int id){
		
		Map<String, Object> respuesta = new HashMap<>();
		
		try {
			
			Persona persona = this.personaService.findById(id);
			
			this.personaService.delete(persona);
			
			respuesta.put("mensaje", "Cliente eliminado con exito");
			
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
			
		} catch (DataAccessException e) {
			
			respuesta.put("mensaje", "Error al eliminar el cliente de la base de datos");
			respuesta.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
