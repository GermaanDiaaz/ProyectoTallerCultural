package com.salesianos.dam.tallerCultural.tallerCultural.controller;

import com.salesianos.dam.tallerCultural.tallerCultural.dto.*;
import com.salesianos.dam.tallerCultural.tallerCultural.model.Taller;
import com.salesianos.dam.tallerCultural.tallerCultural.service.TallerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taller")
@RequiredArgsConstructor
@Validated
@Tag(name = "Taller Controller", description = "Un controlador de talleres")
public class TallerController {

    private final TallerService tallerService;

    @PostMapping
    public ResponseEntity<GetTallerDetail> addTaller(
            @Valid @RequestBody EditTallerDto taller) {

        return ResponseEntity.status(201)
                .body(GetTallerDetail.of(tallerService.addTaller(taller)));

    }

    @GetMapping
    public ResponseEntity<List<GetTallerList>> getAllTalleres() {

        List<Taller> result = tallerService.getAllTalleres();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetTallerList::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTallerDetail> getTallerById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(GetTallerDetail.of(tallerService.getTallerById(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTallerDetail> updateTaller(
            @PathVariable Long id,
            @Valid @RequestBody EditTallerDto taller) {

        return ResponseEntity.ok(
                GetTallerDetail.of(tallerService.updateTaller(id, taller)));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaller(@PathVariable Long id) {
        tallerService.deleteTaller(id);
        return ResponseEntity.noContent().build();
    }


}