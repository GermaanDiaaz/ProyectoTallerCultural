package com.salesianos.dam.tallerCultural.tallerCultural.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id @GeneratedValue
    private Long id;

    private String nombreCompleto;

    @ManyToMany
    private Set<Taller> talleres = new HashSet<>();
}
