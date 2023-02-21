package com.biblioteca.rest.bidireccional.apiFiestas.controllers;

import com.biblioteca.rest.bidireccional.apiFiestas.entity.Habilidades;
import com.biblioteca.rest.bidireccional.apiFiestas.entity.Personas;
import com.biblioteca.rest.bidireccional.apiFiestas.repository.HabilidadesRepository;
import com.biblioteca.rest.bidireccional.apiFiestas.repository.PersonaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadesController {

    @Autowired
    private HabilidadesRepository habilidadesRepository;

    @Autowired
    private PersonaRepository personaRepository;


    @GetMapping
    public ResponseEntity<Collection<Habilidades>> findAllHabilidades(){

        return new ResponseEntity<>(habilidadesRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidades> findHabilidadById(@PathVariable Integer id){

        Optional<Habilidades> habilidadesOptional = habilidadesRepository.findById(id);

        if(habilidadesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(habilidadesOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Habilidades> addHabilidad(@PathVariable Integer id,@Valid @RequestBody Habilidades habilidad){

        Optional<Personas> optionalPersona = personaRepository.findById(id);

        if(optionalPersona.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        habilidad.setPersona(optionalPersona.get());

        Habilidades habSaved = habilidadesRepository.save(habilidad);

        return new ResponseEntity<>(habSaved,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Habilidades> updateHabilidad(@PathVariable Integer id,@Valid @RequestBody Habilidades habilidad){

        Optional<Habilidades> optionalHabilidades = habilidadesRepository.findById(id);

        if(optionalHabilidades.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        habilidad.setIdHabilidad(id);
        Habilidades habSaved = habilidadesRepository.save(habilidad);

        return new ResponseEntity<>(habSaved,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Habilidades> removeHabilidad(@PathVariable Integer id){

        Optional<Habilidades> optionalHabilidades = habilidadesRepository.findById(id);

        if(optionalHabilidades.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        habilidadesRepository.delete(optionalHabilidades.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

