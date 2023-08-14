package com.techno_1.springbootdasar.repository

import com.techno_1.springbootdasar.domain.entity.TokenEntity
import com.techno_1.springbootdasar.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository: JpaRepository<TokenEntity, String> {

    fun findIdByToken(token: String): TokenEntity?
    fun findById(id: Int): TokenEntity?
}