package com.forwork.consumer.deadletter.message

import com.forwork.consumer.MessageIfs

data class FailMessage(
        override val email: String,
        override val title: String,
        override val content: String
) : MessageIfs
