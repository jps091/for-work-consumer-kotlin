package com.consumer.consumer.notice.message

import com.consumer.consumer.MessageIfs

data class NoticeMessage(
        override val email: String,
        override val title: String,
        override val content: String
): MessageIfs
