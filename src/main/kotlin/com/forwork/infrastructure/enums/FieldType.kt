package com.forwork.infrastructure.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class FieldType(val description: String) {
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    ANDROID("안드로이드"),
    IOS("IOS"),
    DEVOPS("인프라"),
    AI("인공지능"),
    ;

    companion object{
        @JsonCreator
        fun from(value: String): FieldType{
            return entries.find{ it.name.equals(value, ignoreCase = true)}
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}