package com.salesianos.dam.tallerCultural.tallerCultural.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taller {

    @Id @GeneratedValue
    private Long id;

    private String titulo;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    @ManyToOne
    private Organizador organizador;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "taller_participante",
            joinColumns = @JoinColumn(name = "taller_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private Set<Participante> participantes = new HashSet<>();
    public void addParticipante(Participante p) {
        this.participantes.add(p);
        p.getTalleres().add(this);
    }

    public void removeParticipante(Participante p) {
        this.participantes.remove(p);
        p.getTalleres().remove(this);
    }
}
