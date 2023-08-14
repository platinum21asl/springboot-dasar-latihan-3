package com.techno_1.springbootdasar.domain.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReqAuthLoginDto(


    @field:NotBlank(message = "field username cannot be blank")
    val username: String? = null,

    @field:NotBlank(message = "field username cannot be blank")
    val password: String? = null,
)
