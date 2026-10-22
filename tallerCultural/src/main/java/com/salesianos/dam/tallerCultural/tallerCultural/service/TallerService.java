package com.salesianos.dam.tallerCultural.tallerCultural.service;


import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditTallerDto;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;

    public List<Taller> getAllTalleres() {
        List<Taller> result = tallerRepository.findAll();
        if (result.isEmpty()) {
            throw new RuntimeException("No hay talleres");
        }
        return result;
    }

    public Taller getTallerById(Long id) {
        return tallerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taller no encontrado con id: " + id));
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
                .orElseThrow(() -> new RuntimeException("Taller no encontrado con id: " + id));
    }

    public void deleteTaller(Long id) {
        tallerRepository.deleteById(id);
    }
}