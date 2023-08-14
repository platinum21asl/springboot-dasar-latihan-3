package com.techno_1.springbootdasar.service.impl

import com.techno_1.springbootdasar.domain.dto.request.ReqAuthLoginDto
import com.techno_1.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno_1.springbootdasar.domain.dto.response.ResAuthLoginDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResUserDto
import com.techno_1.springbootdasar.domain.dto.response.ResValidateLoginDto
import com.techno_1.springbootdasar.domain.entity.TokenEntity
import com.techno_1.springbootdasar.exception.CustomExceptionHandler
import com.techno_1.springbootdasar.repository.TokenRepository
import com.techno_1.springbootdasar.repository.UserRepository
import com.techno_1.springbootdasar.service.AuthLoginService
import com.techno_1.springbootdasar.util.JwtGenerator
import org.springframework.stereotype.Service


@Service
class AuthLoginServiceImpl(
    private val tokenRepository: TokenRepository,
    private val usersRepository: UserRepository
) : AuthLoginService {
    override fun authLogin(reqAuthLoginDto: ReqAuthLoginDto): ResBaseDto<Any> {

//         find user by username
        val dataUser = usersRepository.findByUsername(reqAuthLoginDto.username.toString())
            ?: throw CustomExceptionHandler("Username Not Found")
        //
//        // Check UsersPassword  == reqAuthLoginDtoPassword?
        if(reqAuthLoginDto.password != dataUser.password) throw CustomExceptionHandler("Password invalid")
//
//
        val exp =  10 * 60000L // 5 menit  (60000L per Menit)

        val token = JwtGenerator().createJwt(
            reqAuthLoginDto.username!!,
            reqAuthLoginDto.password!!,
            exp
        )

        val data = TokenEntity(
            token = token,
            expired = exp,
            idUser = dataUser
        )

        val dataEntity = tokenRepository.save(data)

        val dataResponse = ResAuthLoginDto(
            id = dataEntity.id,
            token = token,

        )
        val response = ResBaseDto(data = dataResponse)

        return ResBaseDto (data = response)

    }

    override fun authValidateTokenUsers(reqValidateLoginDto: ReqValidateLoginDto): ResBaseDto<ResValidateLoginDto> {
        val data = tokenRepository.findIdByToken(reqValidateLoginDto.token.toString()) ?: throw CustomExceptionHandler("Token Not Found or Expired")

        val response = ResValidateLoginDto(
            id = data.id!!,
            name = data.idUser?.name!!,
            email = data.idUser.email!!,
            username = data.idUser.username!!
        )

        return ResBaseDto(data = response)
    }


}