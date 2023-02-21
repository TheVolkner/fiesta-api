package com.biblioteca.rest.bidireccional.apiFiestas.repository;

import com.biblioteca.rest.bidireccional.apiFiestas.entity.Habilidades;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface HabilidadesRepository extends CrudRepository<Habilidades,Integer> {

    public Collection<Habilidades> findAll();
}
