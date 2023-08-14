package com.techno_1.springbootdasar.controller

import com.techno_1.springbootdasar.domain.dto.request.ReqAuthLoginDto
import com.techno_1.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResDecodeJwtDto
import com.techno_1.springbootdasar.domain.dto.response.ResValidateLoginDto
import com.techno_1.springbootdasar.service.AuthLoginService
import com.techno_1.springbootdasar.service.CrudService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val authService: AuthLoginService
) {

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody reqAuthLoginDto: ReqAuthLoginDto): ResponseEntity<ResBaseDto<Any>> {
        val response = authService.authLogin(reqAuthLoginDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/login/validateToken")
    fun validateToken(@RequestBody reqValidateLoginDto: ReqValidateLoginDto):  ResponseEntity<ResBaseDto<ResValidateLoginDto>>{
        val response = authService.authValidateTokenUsers(reqValidateLoginDto)

        return ResponseEntity.ok().body(response)
    }
}