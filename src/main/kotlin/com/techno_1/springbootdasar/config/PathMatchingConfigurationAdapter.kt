package com.techno_1.springbootdasar.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PathMatchingConfigurationAdapter(
    val requestInterceptor: RequestInterceptor,
    val validateLoginInterceptor: ValidateLoginInterceptor,
    val loginInterceptor: LoginInterceptor,
    val crudInterceptor: CrudInterceptor
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(requestInterceptor)
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/v1/api/auth/login")
        registry.addInterceptor(crudInterceptor).addPathPatterns("/v1/api/crud/users")
        registry.addInterceptor(crudInterceptor).addPathPatterns("/v1/api/crud/users/{id}")
        registry.addInterceptor(crudInterceptor).addPathPatterns("/v1/api/crud/users")
        registry.addInterceptor(loginInterceptor).addPathPatterns("/v1/api/auth/login")
        registry.addInterceptor(validateLoginInterceptor).addPathPatterns("/v1/api/auth/login/validateToken")
    }

}