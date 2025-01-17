package com.consumer.consumer.auth.message

import com.consumer.consumer.MessageIfs

data class AuthMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
