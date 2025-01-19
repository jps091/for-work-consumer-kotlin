package com.forwork.consumer.admin.message

import com.forwork.consumer.MessageIfs

data class AdminInquiryMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
