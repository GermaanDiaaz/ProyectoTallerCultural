package com.salesianos.dam.tallerCultural.tallerCultural.utils;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.OrganizadorRepository;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.ParticipanteRepository;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.TallerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final OrganizadorRepository organizadorRepository;
    private final ParticipanteRepository participanteRepository;
    private final TallerRepository tallerRepository;

    @PostConstruct
    public void initData() {

        /*
            Invocar a los servicios para insertar
            datos de ejemplo
         */

        Organizador o1 = Organizador.builder()
                .nombre("Luismi")
                .email("correo@gmail.com")
                .build();

        Organizador o2 = Organizador.builder()
                .nombre("Miguel")
                .email("correo2@gmail.com")
                .build();

        organizadorRepository.saveAll(List.of(o1, o2));

        Participante p1 = Participante.builder()
                .nombreCompleto("Juan Pérez")
                .build();

        Participante p2 = Participante.builder()
                .nombreCompleto("María García")
                .build();

        Participante p3 = Participante.builder()
                .nombreCompleto("Carlos Gómez")
                .build();

        participanteRepository.saveAll(List.of(p1, p2, p3));

        Taller t1 = Taller.builder()
                .titulo("Taller Python")
                .fechaHoraInicio(LocalDateTime.now())
                .fechaHoraFin(LocalDateTime.now().plusDays(2))
                .organizador(o1)
                .build();

        Taller t2 = Taller.builder()
                .titulo("Taller Java")
                .fechaHoraInicio(LocalDateTime.now())
                .fechaHoraFin(LocalDateTime.now().plusDays(5))
                .organizador(o2)
                .build();


        t1.addParticipante(p1);
        t1.addParticipante(p2);

        t2.addParticipante(p2);
        t2.addParticipante(p3);

        tallerRepository.saveAll(List.of(t1, t2));
    }

}
