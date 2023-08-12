package com.techno_1.springbootdasar.domain.validation.validator

import com.techno_1.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno_1.springbootdasar.domain.validation.CustomClassValidation
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ClassValidator: ConstraintValidator<CustomClassValidation, Object> {
    override fun isValid(value: Object?, context: ConstraintValidatorContext?): Boolean {
        TODO("Not yet implemented")
    }


}