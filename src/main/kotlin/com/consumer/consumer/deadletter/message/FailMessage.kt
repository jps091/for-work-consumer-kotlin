package com.consumer.consumer.deadletter.message

import com.consumer.consumer.MessageIfs

data class FailMessage(
        override val email: String,
        override val title: String,
        override val content: String
) : MessageIfs
