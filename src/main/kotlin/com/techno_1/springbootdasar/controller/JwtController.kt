package com.techno_1.springbootdasar.controller

import com.techno_1.springbootdasar.domain.dto.request.ReqDecodeJwtDto
import com.techno_1.springbootdasar.domain.dto.request.ReqExampleJwtDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResDecodeJwtDto
import com.techno_1.springbootdasar.domain.dto.response.ResExampleJwtDto
import com.techno_1.springbootdasar.util.AESUtils
import com.techno_1.springbootdasar.util.JwtGenerator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.print.attribute.IntegerSyntax
import kotlin.math.exp

@RestController
@RequestMapping("/v1/api/jwt")
class JwtController {

    @PostMapping("/encode")
    fun encode(@RequestBody reqExampleJwtDto: ReqExampleJwtDto): ResponseEntity<ResBaseDto<ResExampleJwtDto>> {
        val exp = 3600000L
        val token = JwtGenerator().createJwt(
            reqExampleJwtDto.id.toString(),
            reqExampleJwtDto.username!!,
            exp
        )
        val dataResponse = ResExampleJwtDto(
            token = token,
            id = reqExampleJwtDto.id
            )
        val response = ResBaseDto(data = dataResponse, code = 200)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/decode")
    fun decode(@RequestBody reqDecodeJwtDto: ReqDecodeJwtDto): ResponseEntity<ResBaseDto<ResDecodeJwtDto>> {
        val claims = JwtGenerator().decodeJWT(reqDecodeJwtDto.token!!)
        val dataResponse = ResDecodeJwtDto(
            id = Integer.valueOf(AESUtils.decrypt(claims.id, "TECHNOSECRET")),
            username = AESUtils.decrypt(claims.subject, "TECHNOSECRET")
        )
        val response = ResBaseDto(
            data = dataResponse
        )

        return ResponseEntity.ok().body(response)
    }
}