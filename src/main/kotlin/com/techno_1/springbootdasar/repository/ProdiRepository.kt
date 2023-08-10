package com.techno_1.springbootdasar.repository

import com.techno_1.springbootdasar.domain.entity.ProdiEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID



interface ProdiRepository: JpaRepository<ProdiEntity, String> {
    fun findById(id: UUID): ProdiEntity?
}