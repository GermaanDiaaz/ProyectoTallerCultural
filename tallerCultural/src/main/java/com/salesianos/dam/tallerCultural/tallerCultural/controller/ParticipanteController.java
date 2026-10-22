package com.salesianos.dam.tallerCultural.tallerCultural.controller;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditParticipanteDto;
import com.salesianos.dam.tallerCultural.tallerCultural.dto.GetParticipanteDetail;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import com.salesianos.dam.tallerCultural.tallerCultural.service.ParticipanteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participante")
@RequiredArgsConstructor
@Validated
@Tag(name="Participante Controller", description = "Un controlador de participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<GetParticipanteDetail> addParticipante(
            @Valid @RequestBody EditParticipanteDto participante) {

        return ResponseEntity.status(201)
                .body(GetParticipanteDetail.of(participanteService.addParticipante(participante)));

    }

    @GetMapping
    public ResponseEntity<List<GetParticipanteDetail>> getAllParticipante() {

        List<Participante> result = participanteService.getAllParticipantes();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetParticipanteDetail::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetParticipanteDetail> getParticipanteById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(GetParticipanteDetail.of(participanteService.getParticipanteById(id)));

    }

}
