package com.consumer.infrastructure.resume.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class ResumeStatus(val description: String) {
    PENDING("대기"),
    ACTIVE("활성"),
    REJECTED("거절"),
    DELETE("삭제");

    companion object{
        @JsonCreator
        fun from(value: String): ResumeStatus{
            return entries.find{ it. name.equals(value, ignoreCase = true) }
                    ?: throw IllegalArgumentException("Invalid Status: $value")
        }
    }
}