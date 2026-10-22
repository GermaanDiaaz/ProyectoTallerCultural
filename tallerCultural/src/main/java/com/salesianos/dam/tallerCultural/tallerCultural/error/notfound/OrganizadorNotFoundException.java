package com.salesianos.dam.tallerCultural.tallerCultural.error.notfound;

public class OrganizadorNotFoundException extends RuntimeException {

    public OrganizadorNotFoundException() {
        super("Organizador not found");
    }

    public  OrganizadorNotFoundException(Long id) {
        super("Organizador not found with id " + id);
    }

    public OrganizadorNotFoundException(String message) {
        super(message);
    }
}
