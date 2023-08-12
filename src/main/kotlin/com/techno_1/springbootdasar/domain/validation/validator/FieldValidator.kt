package com.techno_1.springbootdasar.domain.validation.validator

import com.techno_1.springbootdasar.domain.validation.CustomClassValidation
import com.techno_1.springbootdasar.domain.validation.CustomFieldValidation
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FieldValidator: ConstraintValidator<CustomFieldValidation, String> {
    override fun isValid(value: String?, context:  ConstraintValidatorContext?): Boolean {
        TODO("Not yet implemented")
    }
}