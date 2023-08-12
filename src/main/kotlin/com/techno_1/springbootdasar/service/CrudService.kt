package com.techno_1.springbootdasar.service

import com.techno_1.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno_1.springbootdasar.domain.dto.request.ReqProdiDto
import com.techno_1.springbootdasar.domain.dto.request.ReqUserDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResMahasiswaDto
import com.techno_1.springbootdasar.domain.dto.response.ResUserDto
import java.util.*
import kotlin.collections.ArrayList

interface CrudService {

    fun getAll() : ResBaseDto<ArrayList<ResMahasiswaDto>>
    fun getById(id: Long) : ResBaseDto<ResMahasiswaDto>
    fun insert(reqMahasiswaDto: ReqMahasiswaDto): ResBaseDto<Any>
    fun update(reqMahasiswaDto: ReqMahasiswaDto, id: Long): ResBaseDto<Any>
    fun delete(id: Long): ResBaseDto<Any>

    fun insertProdi(reqProdiDto: ReqProdiDto): ResBaseDto<Any>

    fun insertUsers(reqUserDto: ReqUserDto): ResBaseDto<Any>
    fun getAllUsers(): ResBaseDto<ArrayList<ResUserDto>>

    fun getUsersById(id: Int) : ResBaseDto<ResUserDto>
    fun updateUsers(reqUserDto: ReqUserDto, id: Int): ResBaseDto<Any>
    fun deleteUsers(id: Int): ResBaseDto<Any>

}