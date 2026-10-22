package com.salesianos.dam.tallerCultural.tallerCultural.service;


import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditTallerDto;
import com.salesianos.dam.tallerCultural.tallerCultural.error.ParticipanteYaInscritoException;
import com.salesianos.dam.tallerCultural.tallerCultural.error.notfound.ParticipanteNotFoundException;
import com.salesianos.dam.tallerCultural.tallerCultural.error.notfound.TallerNotFoundException;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.ParticipanteRepository;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;
    private final ParticipanteRepository participanteRepository;

    public List<Taller> getAllTalleres() {
        List<Taller> result = tallerRepository.findAll();
        if (result.isEmpty()) {
            throw new TallerNotFoundException();
        }
        return result;
    }

    public Taller getTallerById(Long id) {
        return tallerRepository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException(id));
    }

    public Taller addTaller(EditTallerDto editTallerDto) {
        Taller t = editTallerDto.to();
        return tallerRepository.save(t);
    }

    public Taller updateTaller(Long id, EditTallerDto editTallerDto) {
        return tallerRepository.findById(id)
                .map(t -> {
                    t.setTitulo(editTallerDto.titulo());
                    t.setFechaHoraInicio(editTallerDto.fechaHoraInicio());
                    t.setFechaHoraFin(editTallerDto.fechaHoraFin());
                    return tallerRepository.save(t);
                })
                .orElseThrow(() -> new TallerNotFoundException(id));
    }

    public void deleteTaller(Long id) {
        if (!tallerRepository.existsById(id)) {
            throw new TallerNotFoundException(id);
        }
        tallerRepository.deleteById(id);
    }

    public Taller inscribirParticipante(Long tallerId, Long participanteId) {
        Taller t = getTallerById(tallerId);
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new ParticipanteNotFoundException(participanteId));

        if (t.getParticipantes().contains(participante)) {
            throw new ParticipanteYaInscritoException("El participante ya está inscrito en este taller");
        }

        t.addParticipante(participante);
        return tallerRepository.save(t);
    }

    public List<Participante> getParticipantesTaller(Long tallerId) {
        Taller t = getTallerById(tallerId);
        return t.getParticipantes();
    }

    public void eliminarInscripcion(Long tallerId, Long participanteId) {
        Taller t = tallerRepository.findById(tallerId).orElse(null);
        Participante participante = participanteRepository.findById(participanteId).orElse(null);

        if (t != null && participante != null && t.getParticipantes().contains(participante)) {
            t.removeParticipante(participante);
            tallerRepository.save(t);
        }
    }
}