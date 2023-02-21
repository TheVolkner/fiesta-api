package com.biblioteca.rest.bidireccional.apiFiestas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name="habilidades")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Habilidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHabilidad;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Nivel nivel;

    @NotNull
    private String nombre;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "persona",referencedColumnName = "idPersona")
    private Personas persona = new Personas();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Habilidades that = (Habilidades) o;
        return idHabilidad != null && Objects.equals(idHabilidad, that.idHabilidad);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
