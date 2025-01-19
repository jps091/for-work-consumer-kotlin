package com.forwork.consumer.password.message

import com.forwork.consumer.MessageIfs

data class PasswordMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
