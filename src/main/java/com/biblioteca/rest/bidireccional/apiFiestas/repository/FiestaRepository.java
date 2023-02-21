package com.biblioteca.rest.bidireccional.apiFiestas.repository;

import com.biblioteca.rest.bidireccional.apiFiestas.entity.Fiestas;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FiestaRepository extends CrudRepository<Fiestas,Integer> {

    public Collection<Fiestas> findAll();
}
