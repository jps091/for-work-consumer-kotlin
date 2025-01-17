package com.consumer.consumer.seller.message

import com.consumer.consumer.MessageIfs

data class SalesRequestResultMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
