package com.biblioteca.rest.bidireccional.apiFiestas.controllers;

import com.biblioteca.rest.bidireccional.apiFiestas.repository.FiestaRepository;
import com.biblioteca.rest.bidireccional.apiFiestas.repository.PersonaRepository;
import com.biblioteca.rest.bidireccional.apiFiestas.entity.Fiestas;
import com.biblioteca.rest.bidireccional.apiFiestas.entity.Personas;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private FiestaRepository fiestaRepository;

    @GetMapping
    public ResponseEntity<Collection<Personas>> findAllPersonas(){

        return new ResponseEntity<>(personaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personas> findPersonaById(@PathVariable Integer id){

        Optional<Personas> personasOptional = personaRepository.findById(id);

        if(!personasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(personasOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personas> addPersona(@Valid @RequestBody Personas personas){

        return new ResponseEntity<>(personaRepository.save(personas),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personas> updatePersona(@PathVariable Integer id,@Valid @RequestBody Personas persona){

        Optional<Personas> personasOptional = personaRepository.findById(id);

        if(!personasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        persona.setIdPersona(id);
        personaRepository.save(persona);

        return new ResponseEntity<>(persona,HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Personas> deletePersona(@PathVariable Integer id){

        Optional<Personas> personasOptional = personaRepository.findById(id);

        if(!personasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personaRepository.delete(personasOptional.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/{id}/fiestas")
    public ResponseEntity<Collection<Fiestas>> getPersonaAllFiestasById(@PathVariable Integer id){

        Optional<Personas> personasOptional = personaRepository.findById(id);

        if(!personasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(personasOptional.get().getFiestas(),HttpStatus.OK);

    }

    @PostMapping("/{id}/fiestas")
    public ResponseEntity<Personas> addPersonaFiesta(@PathVariable Integer id, @Valid @RequestBody Fiestas fiesta){

        Optional<Personas> personasOptional = personaRepository.findById(id);

        if(personasOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Personas persona = personasOptional.get();

        Fiestas fiestaSaved = fiestaRepository.save(fiesta);

        Set<Fiestas> f;

        if(persona.getFiestas().isEmpty()){
            f = new HashSet<>();
        } else {
            f = persona.getFiestas();
        }

        f.add(fiestaSaved);
        persona.setFiestas(f);
        personaRepository.save(persona);
        return new ResponseEntity<>(persona,HttpStatus.OK);

    }

}
