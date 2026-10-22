package com.salesianos.dam.tallerCultural.tallerCultural.validation;

import com.salesianos.dam.tallerCultural.tallerCultural.repo.TallerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueTallerTitleValidator implements ConstraintValidator<UniqueTallerTitle, String> {

    @Autowired
    private TallerRepository tallerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return true;
        }
        return !tallerRepository.existsByTituloIgnoreCase(value.trim());
    }
}