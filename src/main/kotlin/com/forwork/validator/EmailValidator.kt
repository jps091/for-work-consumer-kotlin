package com.forwork.validator

import com.forwork.consumer.MessageIfs
import com.forwork.exception.InvalidEmailException

object EmailValidator {

    private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    private val EMAIL_PATTERN = Regex(EMAIL_REGEX)

    fun validate(message: MessageIfs){
        // 이메일 검증
        if(message.email.isNullOrBlank() || !EMAIL_PATTERN.matches(message.email!!)){
            throw InvalidEmailException("Invalid email format: ${message.email}")
        }

        if(message.title.isNullOrBlank()){
            throw InvalidEmailException("Email title cannot be null or blank.")
        }

        if (message.content.isNullOrBlank()) {
            throw InvalidEmailException("Email content cannot be null or blank.")
        }
    }
}