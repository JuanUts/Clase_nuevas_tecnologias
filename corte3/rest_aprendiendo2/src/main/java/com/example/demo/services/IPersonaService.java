package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Persona;

public interface IPersonaService {

	
	List<Persona> findAll();
	
	Persona findById(int id);
	
	Persona save(Persona persona);
	
	void delete(Persona persona);
}
