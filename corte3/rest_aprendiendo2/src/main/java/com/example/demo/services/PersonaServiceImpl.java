package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Persona;
import com.example.demo.repo.IPersonaRepo;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private IPersonaRepo personaRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return this.personaRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(int id) {
		return this.personaRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		
		return this.personaRepo.save(persona);
	}

	@Override
	@Transactional
	public void delete(Persona persona) {
		this.personaRepo.delete(persona);
	}

}
