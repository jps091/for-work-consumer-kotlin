package com.consumer.consumer.admin.message

import com.consumer.consumer.MessageIfs

data class AdminInquiryMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
