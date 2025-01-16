package com.consumer.infrastructure.user.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class UserStatus(val description: String){
    ADMIN("관리자"),
    USER("일반 유저"),
    DELETE("탈퇴 유저");


    companion object{
        @JvmStatic
        @JsonCreator
        fun from(value: String): UserStatus{
            return entries.find { it.name.equals(value, ignoreCase = true) }
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}