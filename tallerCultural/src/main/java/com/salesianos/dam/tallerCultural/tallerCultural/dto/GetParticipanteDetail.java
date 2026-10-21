package com.salesianos.dam.tallerCultural.tallerCultural.dto;

import com.salesianos.dam.tallerCultural.tallerCultural.model.Participante;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "GetParticipanteDetail",
        description = "Detalles de un participante"
)
public record GetParticipanteDetail(

        @Schema(
                description = "Identificador único del participante",
                example = "15"
        )
        Long id,

        @Schema(
                description = "Nombre completo del participante",
                example = "Luis Miguel López Magaña"
        )
        String nombreCompleto

) {

    public static GetParticipanteDetail of(Participante p) {
        return new GetParticipanteDetail(
                p.getId(),
                p.getNombreCompleto()
        );
    }
}
