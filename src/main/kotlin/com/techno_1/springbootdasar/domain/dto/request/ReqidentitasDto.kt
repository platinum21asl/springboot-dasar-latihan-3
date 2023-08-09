package com.techno_1.springbootdasar.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ReqidentitasDto(



    @field:JsonProperty(value = "first_name")
    val firstName : String? =null,

    @field:JsonProperty(value = "last_name")
    val lastName : String? =null,

    @field:JsonProperty(value = "age")
    val age : Long? = null
)
