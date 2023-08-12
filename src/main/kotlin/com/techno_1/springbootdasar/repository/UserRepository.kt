package com.techno_1.springbootdasar.repository

import com.techno_1.springbootdasar.domain.entity.ProdiEntity
import com.techno_1.springbootdasar.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface UserRepository: JpaRepository<UserEntity, String> {

    fun findById(id: Int): UserEntity?

    @Modifying
    @Transactional
    @Query(value = "DELETE UserEntity WHERE id = :id")
    fun deleteId(id: Int): Int?
}