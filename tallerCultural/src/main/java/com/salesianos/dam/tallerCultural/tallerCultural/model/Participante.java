package com.salesianos.dam.tallerCultural.tallerCultural.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id @GeneratedValue
    private Long id;

    private String nombreCompleto;

    @Builder.Default
    @ManyToMany(mappedBy = "participantes")
    private Set<Taller> talleres = new HashSet<>();
}
