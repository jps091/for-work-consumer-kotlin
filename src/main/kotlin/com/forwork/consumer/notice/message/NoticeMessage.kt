package com.forwork.consumer.notice.message

import com.forwork.consumer.MessageIfs

data class NoticeMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
