package com.techno_1.springbootdasar.domain.entity


import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class UserEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Column(name = "id", columnDefinition = "bigint")
    val id: Int? = null,

    @field:Column(name="uuid", columnDefinition = "uuid")
    val uid: UUID? = null,

    @field:Column(name = "name", columnDefinition = "varchar(100)")
    val name: String? = null,

    @field:Column(name = "email", columnDefinition = "varchar(100)")
    val email: String? = null,

    @field:Column(name = "username", columnDefinition = "varchar(100)")
    val username: String? = null,

    @field:Column(name = "password", columnDefinition = "varchar(100)")
    val password: String? = null,

    )
