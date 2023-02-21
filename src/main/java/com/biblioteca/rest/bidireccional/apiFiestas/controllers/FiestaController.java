package com.biblioteca.rest.bidireccional.apiFiestas.controllers;

import com.biblioteca.rest.bidireccional.apiFiestas.repository.FiestaRepository;
import com.biblioteca.rest.bidireccional.apiFiestas.entity.Fiestas;
import com.biblioteca.rest.bidireccional.apiFiestas.entity.Personas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/fiestas")
public class FiestaController {

    @Autowired
    private FiestaRepository fiestaRepository;

    @GetMapping
    public ResponseEntity<Collection<Fiestas>> findAllFiestas(){

        return new ResponseEntity<>(fiestaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fiestas> findPersonaById(@PathVariable Integer id){

        Optional<Fiestas> FiestasOptional = fiestaRepository.findById(id);

        if(!FiestasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(FiestasOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fiestas> addPersona(@Valid @RequestBody Fiestas fiesta){

        return new ResponseEntity<>(fiestaRepository.save(fiesta),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fiestas> updatePersona(@PathVariable Integer id,@Valid @RequestBody Fiestas fiesta){

        Optional<Fiestas> FiestasOptional = fiestaRepository.findById(id);

        if(!FiestasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        fiesta.setIdFiesta(id);
        fiestaRepository.save(fiesta);

        return new ResponseEntity<>(fiesta,HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Fiestas> deletePersona(@PathVariable Integer id){

        Optional<Fiestas> FiestasOptional = fiestaRepository.findById(id);

        if(!FiestasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        fiestaRepository.delete(FiestasOptional.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/{id}/personas")
    public ResponseEntity<Collection<Personas>> getFiestaAllPersonasById(@PathVariable Integer id){

        Optional<Fiestas> FiestasOptional = fiestaRepository.findById(id);

        if(!FiestasOptional.isPresent()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(FiestasOptional.get().getPersonas(),HttpStatus.OK);

    }
}
