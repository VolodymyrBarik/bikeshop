package org.bikeshop.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bikeshop.dto.request.WholesaleUserRegistrationRequestDto;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,
        WholesaleUserRegistrationRequestDto> {

    @Override
    public boolean isValid(WholesaleUserRegistrationRequestDto dto,
                           ConstraintValidatorContext context) {
        return dto.getPassword().equals(dto.getRepeatPassword());
    }
}
