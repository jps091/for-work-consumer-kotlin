package com.forwork.consumer.auth.message

import com.forwork.consumer.MessageIfs

data class AuthMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
