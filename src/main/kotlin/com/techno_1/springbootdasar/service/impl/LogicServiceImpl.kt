package com.techno_1.springbootdasar.service.impl

import com.techno_1.springbootdasar.domain.dto.request.ReqOperationDto
import com.techno_1.springbootdasar.domain.dto.request.ReqidentitasDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResFullNameDto
import com.techno_1.springbootdasar.domain.dto.response.ResResultOperationDto

import com.techno_1.springbootdasar.service.LogicService
import com.thedeanda.lorem.LoremIpsum
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam

@Service
class LogicServiceImpl : LogicService {
    private val lorem = LoremIpsum.getInstance()
    override fun printName(name: String) {
        println("My name is : $name")
    }

    override fun oddsOrEvent(number: Int): String {
        if (number % 2 == 0) {
            return "Event"
        } else {
            return "Odds"
        }

    }

    override fun fullName(reqidentitasDto: ReqidentitasDto): ResBaseDto<ResFullNameDto> {
        val fullNameTemp = reqidentitasDto.firstName + " " + reqidentitasDto.lastName

        val resFullName = ResFullNameDto(
            firstName = reqidentitasDto.firstName,
            lastName = reqidentitasDto.lastName,
            fullName = fullNameTemp,
        )



        return ResBaseDto(
            data = resFullName
        )
    }

    override fun resultMultiple(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto> {
        val result = reqOperationDto.valueA * reqOperationDto.valueB


        val data = ResResultOperationDto(
            valueA = reqOperationDto.valueA,
            valueB = reqOperationDto.valueB,
            resultAB = result,
        )

        return ResBaseDto(
            data = data
        )

    }

    override fun resultDivision(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto> {
        val result = reqOperationDto.valueA / reqOperationDto.valueB


        val data = ResResultOperationDto(
            valueA = reqOperationDto.valueA,
            valueB = reqOperationDto.valueB,
            resultAB = result,
        )

        return ResBaseDto(
            data = data
        )

    }

    override fun resultAddition(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto> {
        val result = reqOperationDto.valueA + reqOperationDto.valueB


        val data = ResResultOperationDto(
            valueA = reqOperationDto.valueA,
            valueB = reqOperationDto.valueB,
            resultAB = result,
        )

        return ResBaseDto(
            data = data
        )

    }

    override fun resultSubtraction(reqOperationDto: ReqOperationDto): ResBaseDto<ResResultOperationDto> {
        val result = reqOperationDto.valueA - reqOperationDto.valueB


        val data = ResResultOperationDto(
            valueA = reqOperationDto.valueA,
            valueB = reqOperationDto.valueB,
            resultAB = result,
        )


        return ResBaseDto(
            data = data
        )


    }

    override fun randomPerson(size: Int): ResBaseDto<MutableList<ResFullNameDto>> {
        val randomNames = mutableListOf<ResFullNameDto>()


        for (i in 1..size) {
            val firstName = lorem.firstName
            val lastName = lorem.lastName
            val fullName = "$firstName $lastName"
            val nameResponse = ResFullNameDto(firstName, lastName, fullName)

          randomNames.add(nameResponse)
        }
        return ResBaseDto(
            data = randomNames
        )
    }


}


