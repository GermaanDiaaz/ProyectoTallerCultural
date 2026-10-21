package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Organizador;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetOrganizadorDetail",
        description = "Detalles de un organizador"
)
public record GetOrganizadorDetail(
        @Schema(
                description = "Identificador único del organizador",
                example = "15"
        )
        Long id,

        @Schema(
                description = "Nombre del organizador",
                example = "Luismi"
        )
        String nombre,

        @Schema(
                description = "Correo del organizador",
                example = "correo@gmail.com"
        )
        String email
) {

    public static GetOrganizadorDetail of(Organizador o) {
        return new GetOrganizadorDetail(
                o.getId(),
                o.getNombre(),
                o.getEmail()
        );
    }

}