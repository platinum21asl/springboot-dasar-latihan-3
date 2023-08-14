package com.techno_1.springbootdasar.domain.entity

import java.time.LocalDateTime
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "token")
data class TokenEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", columnDefinition = "bigint")
    val id: Int? = null,


    @field:Column(name = "token", columnDefinition = "varchar(255)")
    val token: String? = null,

    @field:Column(name = "expired", columnDefinition = "bigint")
    val expired: Long? = null,

    @OneToOne
    @field:JoinColumn(name = "id_user", referencedColumnName = "id", columnDefinition = "bigint")
    val idUser: UserEntity? = null,
)
