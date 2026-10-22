package com.salesianos.dam.tallerCultural.tallerCultural.service;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditOrganizadorDto;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import com.salesianos.dam.tallerCultural.tallerCultural.repo.OrganizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public List<Organizador> getAllOrganizadores() {
        List<Organizador> result = organizadorRepository.findAll();
        if (result.isEmpty()) {
            throw new RuntimeException("No hay organizadores");
        }
        return result;
    }

    public Organizador getOrganizadorById(Long id) {
        return organizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado con id: " + id));
    }

    public Organizador addOrganizador(EditOrganizadorDto editOrganizadorDto) {
        Organizador o = editOrganizadorDto.to();
        return organizadorRepository.save(o);
    }

    public Organizador updateOrganizador(Long id, EditOrganizadorDto editOrganizadorDto) {
        return organizadorRepository.findById(id)
                .map(o -> {
                    o.setNombre(editOrganizadorDto.nombre());
                    o.setEmail(editOrganizadorDto.email());
                    return organizadorRepository.save(o);
                })
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado con id: " + id));
    }

    public void deleteOrganizador(Long id) {
        organizadorRepository.deleteById(id);
    }
}
