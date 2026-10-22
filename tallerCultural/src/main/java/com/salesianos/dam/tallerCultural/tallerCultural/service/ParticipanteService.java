package com.salesianos.dam.tallerCultural.tallerCultural.service;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditParticipanteDto;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.ParticipanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public List<Participante> getAllParticipantes() {
        List<Participante> result = participanteRepository.findAll();
        if (result.isEmpty()) {
            throw new RuntimeException("No hay participantes");
        }
        return result;
    }

    public Participante getParticipanteById(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con id: " + id));
    }

    public Participante addParticipante(EditParticipanteDto editParticipanteDto) {
        Participante p = editParticipanteDto.to();
        return participanteRepository.save(p);
    }

    public Participante updateParticipante(Long id, EditParticipanteDto editParticipanteDto) {
        return participanteRepository.findById(id)
                .map(p -> {
                    p.setNombreCompleto(editParticipanteDto.nombreCompleto());
                    return participanteRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con id: " + id));
    }

    public void deleteParticipante(Long id) {
        participanteRepository.deleteById(id);
    }
}


