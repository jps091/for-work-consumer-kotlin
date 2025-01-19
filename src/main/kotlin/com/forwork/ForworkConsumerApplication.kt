package com.forwork

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ForworkConsumerApplication

fun main(args: Array<String>){
    runApplication<ForworkConsumerApplication>(*args)
}