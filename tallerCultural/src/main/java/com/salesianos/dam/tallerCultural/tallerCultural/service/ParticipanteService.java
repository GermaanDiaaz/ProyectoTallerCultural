package com.salesianos.dam.tallerCultural.tallerCultural.service;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditParticipanteDto;
import com.salesianos.dam.tallerCultural.tallerCultural.error.notfound.ParticipanteNotFoundException;
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
            throw new ParticipanteNotFoundException();
        }
        return result;
    }

    public Participante getParticipanteById(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ParticipanteNotFoundException(id));
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
                .orElseThrow(() -> new ParticipanteNotFoundException(id));
    }

    public void deleteParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new ParticipanteNotFoundException(id);
        }
        participanteRepository.deleteById(id);
    }
}


