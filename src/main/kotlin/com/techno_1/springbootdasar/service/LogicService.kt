package com.techno_1.springbootdasar.service

import com.techno_1.springbootdasar.domain.dto.request.ReqOperationDto
import com.techno_1.springbootdasar.domain.dto.request.ReqidentitasDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResFullNameDto
import com.techno_1.springbootdasar.domain.dto.response.ResResultOperationDto
import org.springframework.web.bind.annotation.RequestParam

interface LogicService {

    fun printName(name: String)
    fun oddsOrEvent(number: Int): String

    fun fullName(reqidentitasDto: ReqidentitasDto): ResBaseDto<ResFullNameDto>

    fun resultMultiple(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto>
    fun resultDivision(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto>
    fun resultAddition(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto>
    fun resultSubtraction(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto>

    fun randomPerson(@RequestParam size: Int): ResBaseDto<MutableList<ResFullNameDto>>
}