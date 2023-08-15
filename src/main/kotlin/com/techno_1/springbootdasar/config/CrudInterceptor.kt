package com.techno_1.springbootdasar.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno_1.springbootdasar.exception.CustomExceptionHandler
import com.techno_1.springbootdasar.repository.TokenRepository
import com.techno_1.springbootdasar.util.AESUtils
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class CrudInterceptor(
    private val tokenRepository: TokenRepository,
): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("api-key")
        val token = request.getHeader("token") // Token yang dienkripsi dari header


        val data = tokenRepository.findIdByToken(token) // Cari data token di basis data berdasarkan token yang telah didekripsi

        return if (apiKey == "123-456-789" && data != null) {
            true
        }else{
            val result = mapOf<String, String>("status" to "F", "message" to "you don't have permission access CRUD")
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            false
        }

    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {

    }

    fun convertObjectToJson(dto : Map<String, String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}