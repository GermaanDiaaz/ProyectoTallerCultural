package com.salesianos.dam.tallerCultural.tallerCultural.error.notfound;

public class TallerNotFoundException extends RuntimeException {

  public TallerNotFoundException() {
    super("Taller not found");
  }

  public  TallerNotFoundException(Long id) {
    super("Taller not found with id " + id);
  }

  public TallerNotFoundException(String message) {
    super(message);
  }
}
