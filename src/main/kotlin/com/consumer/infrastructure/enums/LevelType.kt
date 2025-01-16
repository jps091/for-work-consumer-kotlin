package com.consumer.infrastructure.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class LevelType(val description: String) {

    NEW("신입"),
    JUNIOR("주니어"),
    SENIOR("시니어");

    companion object{
        @JsonCreator
        fun from(value: String): LevelType{
            return entries.find { it.name.equals(value, ignoreCase = true) }
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}