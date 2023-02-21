package com.biblioteca.rest.bidireccional.apiFiestas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="fiestas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Fiestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFiesta;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fiesta_fecha;

    @NotNull
    private String ubicacion;

    @ManyToMany(mappedBy = "fiestas",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Personas> personas = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fiestas fiestas = (Fiestas) o;
        return idFiesta != null && Objects.equals(idFiesta, fiestas.idFiesta);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
