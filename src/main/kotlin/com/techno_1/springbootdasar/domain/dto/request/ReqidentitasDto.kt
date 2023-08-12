package com.techno_1.springbootdasar.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class ReqidentitasDto(



    @field:JsonProperty(value = "first_name")
    @field:NotEmpty
    val firstName : String? =null,

    @field:JsonProperty(value = "last_name")
    @field:NotEmpty
    val lastName : String? =null,

//    @field:JsonProperty(value = "full_name")
//    val fullName : String? =null,

    @field:JsonProperty(value = "age")
    @field:NotEmpty
    val age : Long? = null
)
