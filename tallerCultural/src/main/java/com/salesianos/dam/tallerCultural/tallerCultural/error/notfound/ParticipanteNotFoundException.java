package com.salesianos.dam.tallerCultural.tallerCultural.error.notfound;

public class ParticipanteNotFoundException extends RuntimeException {

    public ParticipanteNotFoundException() {
        super("Participante not found");
    }

    public  ParticipanteNotFoundException(Long id) {
        super("Participante not found with id " + id);
    }

    public ParticipanteNotFoundException(String message) {
        super(message);
    }
}
