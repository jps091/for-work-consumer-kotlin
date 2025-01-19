package com.forwork.consumer.seller.message

import com.forwork.consumer.MessageIfs

data class SellingMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
