package com.techno_1.springbootdasar.service

import com.techno_1.springbootdasar.domain.dto.request.ReqAuthLoginDto
import com.techno_1.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResValidateLoginDto

interface AuthLoginService {

    fun authLogin(reqAuthLoginDto: ReqAuthLoginDto) : ResBaseDto<Any>

    fun authValidateTokenUsers(reqValidateLoginDto: ReqValidateLoginDto): ResBaseDto<Any>

    fun validateToken(auth: String): Boolean
}