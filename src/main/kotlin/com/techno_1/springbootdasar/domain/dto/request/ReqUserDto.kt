package com.techno_1.springbootdasar.domain.dto.request

import com.techno_1.springbootdasar.domain.validation.CustomUniqueValidation
import java.util.*
import javax.persistence.Column
import javax.validation.constraints.*

data class ReqUserDto(


    @field:NotNull(message = "field name cannot be null")
    @field:Size(max = 100, message = "Maximum length is 100 characters")
    @field:Pattern(regexp = "^[a-zA-Z]*$", message = "Name Must contain only letters")
    val name: String,

    @field:NotBlank(message = "field username cannot be blank")
    @field:CustomUniqueValidation(message = "field email must be unique")
    @field:Email(message = "email not valid")
    val email: String,

    @field:NotBlank(message = "field username cannot be blank")
    @field:Size(max = 32, message = "Maximum length is 32 characters")
    @field:CustomUniqueValidation(message = "field username must be unique")
    val username: String,

    @field:NotBlank(message = "field username cannot be blank")
    @field:Size(max = 32, message = "Maximum length is 32 characters")
    val password: String
)
