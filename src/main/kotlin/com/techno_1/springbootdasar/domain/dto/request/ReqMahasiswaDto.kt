package com.techno_1.springbootdasar.domain.dto.request


import javax.validation.constraints.*


data class ReqMahasiswaDto(


    @field:NotNull(message = "field  nim cannot be null")
    val nim: Long? = null,

    @field:NotBlank(message = "field nama cannot be blank")
    val nama: String? = null,

    @field:NotBlank(message = "field gender cannot be blank")
    val gender: String? = null,

    @field:NotEmpty(message = "field alamat cannot be empty")
    val alamat: String? = null,

    @field:NotBlank(message = "field idProdi cannot be blank")
    @field:Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Invalid UUID format"
    )
    val idProdi : String? = null
)
