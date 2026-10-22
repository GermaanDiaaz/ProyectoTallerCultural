package com.salesianos.dam.tallerCultural.tallerCultural.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(
            name = "taller_participante",
            joinColumns = @JoinColumn(name = "taller_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantes = new ArrayList<>();

    public void addParticipante(Participante p) {
        this.participantes.add(p);
        p.getTalleres().add(this);
    }

    public void removeParticipante(Participante p) {
        this.participantes.remove(p);
        p.getTalleres().remove(this);
    }
}
