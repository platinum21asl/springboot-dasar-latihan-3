package com.techno_1.springbootdasar.config
import com.techno_1.springbootdasar.service.LogicService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ContohConfig(
    private val logicService: LogicService
) {

    @Bean
    fun printName(){
        logicService.printName("Daniel Renato")
    }



    @Bean
    fun getOddsOrEvent(){
        val result : String =  logicService.oddsOrEvent(20)

        println("Number result is $result")

    }
}