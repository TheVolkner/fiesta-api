package com.biblioteca.rest.bidireccional.apiFiestas.repository;

import com.biblioteca.rest.bidireccional.apiFiestas.entity.Personas;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PersonaRepository extends CrudRepository<Personas,Integer> {

    public Collection<Personas> findAll();
}
