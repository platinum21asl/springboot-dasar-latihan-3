package com.techno_1.springbootdasar.repository

import com.techno_1.springbootdasar.domain.entity.MahasiswaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface MahasiswaRepository: JpaRepository<MahasiswaEntity, String> {
    fun findByNim(nim: Long): MahasiswaEntity?
    fun findById(id: Long): MahasiswaEntity?

    @Modifying
    @Transactional
    @Query(value = "DELETE MahasiswaEntity WHERE id = :id")
    fun deleteId(id: Long): Int?
}