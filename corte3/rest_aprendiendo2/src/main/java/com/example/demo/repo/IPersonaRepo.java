package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{

}
