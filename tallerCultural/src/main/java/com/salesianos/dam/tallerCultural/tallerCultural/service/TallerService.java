package com.salesianos.dam.tallerCultural.tallerCultural.service;


import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditTallerDto;
import com.salesianos.dam.tallerCultural.tallerCultural.error.ParticipanteYaInscritoException;
import com.salesianos.dam.tallerCultural.tallerCultural.error.TallerYaExisteException;
import com.salesianos.dam.tallerCultural.tallerCultural.error.notfound.ParticipanteNotFoundException;
import com.salesianos.dam.tallerCultural.tallerCultural.error.notfound.TallerNotFoundException;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.ParticipanteRepository;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        String titulo = editTallerDto.titulo().trim();

        if (editTallerDto.fechaHoraFin().isBefore(editTallerDto.fechaHoraInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
        }

        if (tallerRepository.existsByTituloIgnoreCase(titulo)) {
            throw new TallerYaExisteException("Ya existe un taller con el título: " + titulo);
        }

        Taller t = editTallerDto.to();
        return tallerRepository.save(t);
    }



    public Taller updateTaller(Long id, EditTallerDto editTallerDto) {
        String nuevoTitulo = editTallerDto.titulo().trim();

        if (editTallerDto.fechaHoraFin().isBefore(editTallerDto.fechaHoraInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
        }

        if (!getTallerById(id).getTitulo().equalsIgnoreCase(nuevoTitulo)
                && tallerRepository.existsByTituloIgnoreCase(nuevoTitulo)) {
            throw new TallerYaExisteException("Ya existe otro taller con el título: " + nuevoTitulo);
        }
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

    public Set<Participante> getParticipantesTaller(Long tallerId) {
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