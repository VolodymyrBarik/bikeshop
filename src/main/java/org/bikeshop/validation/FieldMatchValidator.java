package org.bikeshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bikeshop.dto.request.UserRegistrationRequestDto;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,
        UserRegistrationRequestDto> {

    @Override
    public boolean isValid(UserRegistrationRequestDto dto,
                           ConstraintValidatorContext context) {
        return dto.getPassword().equals(dto.getRepeatPassword());
    }
}
