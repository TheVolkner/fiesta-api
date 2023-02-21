package com.biblioteca.rest.bidireccional.apiFiestas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="personas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Personas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    @NotNull
    private String nombre;

    @NotNull
    private Integer edad;

    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL)
    private Set<Habilidades> habilidades = new HashSet<>();

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "personas_fiestas", joinColumns = @JoinColumn(name = "idPersona", referencedColumnName = "idPersona"),
    inverseJoinColumns = @JoinColumn(name="idFiesta",referencedColumnName = "idFiesta"))
    private Set<Fiestas> fiestas = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Personas personas = (Personas) o;
        return idPersona != null && Objects.equals(idPersona, personas.idPersona);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
