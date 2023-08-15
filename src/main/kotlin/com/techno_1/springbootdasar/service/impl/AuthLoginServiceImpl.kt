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
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


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
        var expiredJwt = System.currentTimeMillis()+600000L
        val expiredDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(expiredJwt),ZoneId.systemDefault())
        val token = JwtGenerator().createJwt(
            dataUser.id.toString(),
            reqAuthLoginDto.password!!,
            expiredJwt
        )

        val data = TokenEntity(
            token = token,
            expired = expiredDate,
            idUser = dataUser
        )

        val dataEntity = tokenRepository.save(data)

        val dataResponse = ResAuthLoginDto(
            id = dataEntity.id,
            token = token,

        )
        return ResBaseDto(data = dataResponse)
    }

    override fun authValidateTokenUsers(reqValidateLoginDto: ReqValidateLoginDto): ResBaseDto<Any> {
        val data = tokenRepository.findIdByToken(reqValidateLoginDto.token.toString()) ?: throw CustomExceptionHandler("Token Not Found or Expired")

        val isTokenValid = validateToken(reqValidateLoginDto.token.toString())
        println(data.expired)
        val response = mapOf(
            "id" to data.idUser?.id,
            "name" to data.idUser?.name,
            "username" to data.idUser?.username,
            "email" to data.idUser?.email
        )

        return ResBaseDto(data = response)
    }

    override fun validateToken(auth: String): Boolean {
        val data = tokenRepository.findIdByToken(auth) ?: throw CustomExceptionHandler("Token not valid")
        val dateTimeString = data.expired.toString()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter)
        val currentTime = LocalDateTime.now()
        if (currentTime.isAfter(dateTime)) {
            throw CustomExceptionHandler("Waktu token telah habis")
        }
        return true
    }



}