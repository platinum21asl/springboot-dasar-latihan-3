package com.techno_1.springbootdasar.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResResultOperationDto(

    @field:JsonProperty(value = "value_a")
    val valueA:  Float,

    @field:JsonProperty(value = "value_b")
    val valueB:  Float,

    @field:JsonProperty(value = "resultAB")
    val resultAB:  Float
)
