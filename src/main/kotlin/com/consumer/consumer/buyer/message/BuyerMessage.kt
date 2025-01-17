package com.consumer.consumer.buyer.message

import com.consumer.consumer.MessageIfs

data class BuyerMessage(
        override val email: String,
        override val title: String,
        override val content: String,
        val orderId: Long,
        var resumeId: Long
): MessageIfs
