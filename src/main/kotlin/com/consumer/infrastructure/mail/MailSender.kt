package com.consumer.infrastructure.mail

import com.consumer.consumer.MessageIfs


interface MailSender {
    fun send(message: MessageIfs)
    fun sendBySes(message: MessageIfs)
}