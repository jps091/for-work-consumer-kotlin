package com.forwork.infrastructure.mail

import com.forwork.consumer.MessageIfs


interface MailSender {
    fun send(message: MessageIfs)
    fun sendBySes(message: MessageIfs)
}