package com.techno_1.springbootdasar.domain.validation.validator


import com.techno_1.springbootdasar.domain.dto.request.ReqUserDto

import com.techno_1.springbootdasar.domain.validation.CustomUniqueValidation
import com.techno_1.springbootdasar.repository.UserRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueValidator(
    private val userRepository: UserRepository
): ConstraintValidator<CustomUniqueValidation, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        if(value == null){
            return  true
        }

        val data = userRepository.findAll()
        data.forEach {
            if(it.username == value || it.email == value)
                return false
        }

        return  true
    }
}