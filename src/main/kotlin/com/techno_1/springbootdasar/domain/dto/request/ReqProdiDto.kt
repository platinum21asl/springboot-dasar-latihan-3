package com.techno_1.springbootdasar.domain.dto.request

import javax.validation.constraints.NotBlank

data class ReqProdiDto(

    @field:NotBlank(message = "field nama cannot be blank")
    val nama: String? = null,

)
