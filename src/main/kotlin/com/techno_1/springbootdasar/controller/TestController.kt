package com.techno_1.springbootdasar.controller

import com.techno_1.springbootdasar.domain.dto.request.ReqOperationDto
import com.techno_1.springbootdasar.domain.dto.request.ReqidentitasDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResFullNameDto
import com.techno_1.springbootdasar.domain.dto.response.ResIdentitas
import com.techno_1.springbootdasar.domain.dto.response.ResResultOperationDto
import com.techno_1.springbootdasar.service.LogicService
import com.thedeanda.lorem.Lorem
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api")
class TestController(
    private  val logicService: LogicService
) {

    @Value("\${person.name.first}")
    val firstName = ""
    val lastName = "Renato"

    val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/test")
    fun testGetMapping(): ResponseEntity<LinkedHashMap<String, String>> {
        val response: LinkedHashMap<String, String> = LinkedHashMap()

        response["firstName"] = firstName
        response["lastName"] = lastName
        return ResponseEntity.ok(response)

    }

    @GetMapping("/get-age")
    fun getAge(@RequestParam("age") age: String): ResponseEntity<LinkedHashMap<String, String>> {
        val response: LinkedHashMap<String, String> = LinkedHashMap()

        response["firstName"] = firstName
        response["lastName"] = lastName
        response["age"] = age
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-age/{age}")
    fun getAgeByPath(@PathVariable("age") age: String): ResponseEntity<LinkedHashMap<String, String>> {
        val response: LinkedHashMap<String, String> = LinkedHashMap()

        response["firstName"] = firstName
        response["lastName"] = lastName
        response["age"] = age
        return ResponseEntity.ok(response)
    }

    @PostMapping("get-identitas")
    fun getIdentitas(@RequestBody reqidentitasDto: ReqidentitasDto): ResponseEntity<ResBaseDto<ResIdentitas>> {
        log.info("Incoming request : $reqidentitasDto")


        val response: LinkedHashMap<String, String> = LinkedHashMap()

        response["firstName"] = reqidentitasDto.firstName.toString()
        response["lastName"] = reqidentitasDto.lastName.toString()
        response["age"] = reqidentitasDto.age.toString()

        val responseBody = ResIdentitas(
            firstName = reqidentitasDto.firstName,
            lastName = reqidentitasDto.lastName,
            age = reqidentitasDto.age
        )

        val responseBase = ResBaseDto(status = true, message = "Success", data = responseBody)

        return ResponseEntity.ok().body(responseBase)
    }

    @PostMapping("get-fullname")
    fun getFullName(@RequestBody reqidentitasDto: ReqidentitasDto): ResponseEntity<ResBaseDto<ResFullNameDto>> {
        val response = logicService.fullName(reqidentitasDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("calculator/multiple")
    fun getMultiple(@RequestBody reqOperationDto: ReqOperationDto): ResponseEntity<ResBaseDto<ResResultOperationDto>> {
        val response = logicService.resultMultiple(reqOperationDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("calculator/division")
    fun getDivision(@RequestBody reqOperationDto: ReqOperationDto): ResponseEntity<ResBaseDto<ResResultOperationDto>> {
        val response = logicService.resultDivision(reqOperationDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("calculator/addition")
    fun getAddition(@RequestBody reqOperationDto: ReqOperationDto): ResponseEntity<ResBaseDto<ResResultOperationDto>> {
        val response = logicService.resultAddition(reqOperationDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("calculator/subtraction")
    fun getSubtraction(@RequestBody reqOperationDto: ReqOperationDto): ResponseEntity<ResBaseDto<ResResultOperationDto>> {
        val response = logicService.resultSubtraction(reqOperationDto)

        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/random/name")
    fun getRandomNames(@RequestParam size: Int): ResponseEntity<ResBaseDto<MutableList<ResFullNameDto>>> {
        val response = logicService.randomPerson(size)

        return ResponseEntity.ok().body(response)
    }
}