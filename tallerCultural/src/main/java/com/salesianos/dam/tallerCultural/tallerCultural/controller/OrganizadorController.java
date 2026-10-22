package com.salesianos.dam.tallerCultural.tallerCultural.controller;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.EditOrganizadorDto;
import com.salesianos.dam.tallerCultural.tallerCultural.dto.GetOrganizadorDetail;
import com.salesianos.dam.tallerCultural.tallerCultural.dto.GetOrganizadorList;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import com.salesianos.dam.tallerCultural.tallerCultural.service.OrganizadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizador")
@RequiredArgsConstructor
@Validated
@Tag(name="Organizador Controller", description = "Un controlador de organizadores")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    @PostMapping
    public ResponseEntity<GetOrganizadorDetail> addOrganizador(
            @Valid @RequestBody EditOrganizadorDto organizador) {

        return ResponseEntity.status(201)
                .body(GetOrganizadorDetail.of(organizadorService.addOrganizador(organizador)));

    }

    @GetMapping
    public ResponseEntity<List<GetOrganizadorList>> getAllOrganizadores() {

        List<Organizador> result = organizadorService.getAllOrganizadores();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetOrganizadorList::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizadorDetail> getOrganizadorById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(GetOrganizadorDetail.of(organizadorService.getOrganizadorById(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrganizadorDetail> updateOrganizador(
            @PathVariable Long id,
            @Valid @RequestBody EditOrganizadorDto organizador) {

        return ResponseEntity.ok(
                GetOrganizadorDetail.of(organizadorService.updateOrganizador(id, organizador)));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizador(@PathVariable Long id) {
        organizadorService.deleteOrganizador(id);
        return ResponseEntity.noContent().build();
    }

}