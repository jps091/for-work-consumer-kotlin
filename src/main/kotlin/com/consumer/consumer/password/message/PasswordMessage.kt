package com.consumer.consumer.password.message

import com.consumer.consumer.MessageIfs

data class PasswordMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
